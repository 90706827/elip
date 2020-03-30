package com.zgcenv.elip.official.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.zgcenv.elip.official.config.jwt.JwtTokenUtil;
import com.zgcenv.elip.official.config.jwt.JwtUserDetailService;
import com.zgcenv.elip.official.entity.Users;
import com.zgcenv.elip.official.module.JwtRequest;
import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.service.RedisService;
import com.zgcenv.elip.official.service.UsersService;
import com.zgcenv.elip.official.utils.RespCode;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName JwtAuthenticationController
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/10/30 10:13
 * @Version 1.0
 **/

@Api(tags = "1、首页")
@RestController
@RequestMapping(value = "/web")
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    private int sendTelCount = 50;
    private int telCodeTimeout = 60;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "用户登录")
    @ApiResponses({
            @ApiResponse(code = 700, message = "验证码失效"),
            @ApiResponse(code = 701, message = "验证码不正确"),
            @ApiResponse(code = 702, message = "用户不存在"),
            @ApiResponse(code = 703, message = "用户名称和密码不正确")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        //验证验证码
        Object verCode = redisService.get(jwtRequest.getKey());
        if (StringUtils.isEmpty(verCode)) {
            return ResultJson.failed(RespCode.VER_CODE_EXPIRE);
        }
        if (!verCode.equals(jwtRequest.getVerCode())) {
            return ResultJson.failed(RespCode.VER_CODE_ERROR);
        }
        Users users = usersService.findByUsername(jwtRequest.getUsername());
        if (StringUtils.isEmpty(users)) {
            return ResultJson.failed(RespCode.USER_NOT_FIND);
        }
        if (!passwordEncoder.matches(jwtRequest.getPassword(), users.getPassword())) {
            return ResultJson.failed(RespCode.USERNAME_PASSWORD_ERROR);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails, users.getId());
        redisService.set(userDetails.getUsername(), token, JwtTokenUtil.JWT_TOKEN_VALIDITY);
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("token", token);
        map.put("username", users.getUsername());
        return ResultJson.success(map);
    }

    private Authentication authenticate(String username, String password) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    /**
     * @Author Mr.Jangni
     * @Description 获取验证码
     * @Date 2019/11/18 15:04
     * @Param [request, response]
     * @Return org.springframework.http.ResponseEntity<?>
     **/
    @ApiOperation(value = "获取登录验证码")
    @RequestMapping(value = "/getVerCode", method = RequestMethod.POST)
    public ResponseEntity<?> getVerCode(HttpServletRequest request, HttpServletResponse response) {
        ArithmeticCaptcha specCaptcha = new ArithmeticCaptcha(150, 48, 2);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        logger.info("verCode:{}", verCode);
        // 存入redis并设置过期时间为30分钟
        redisService.set(key, verCode, 3600);
        // 将key和base64返回给前端
        Map<String, Object> map = new HashMap<>(2);
        map.put("key", key);
        map.put("verCode", specCaptcha.toBase64());
        return ResultJson.success(map);
    }
}