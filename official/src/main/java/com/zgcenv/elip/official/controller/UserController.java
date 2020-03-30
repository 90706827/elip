package com.zgcenv.elip.official.controller;

import com.zgcenv.elip.official.config.jwt.JwtTokenUtil;
import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.query.UserQuery;
import com.zgcenv.elip.official.query.UserSave;
import com.zgcenv.elip.official.query.UserUpdate;
import com.zgcenv.elip.official.service.RedisService;
import com.zgcenv.elip.official.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName UserController
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/10/21 23:32
 * @Version 1.0
 **/
@Api(tags = "2、用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UsersService usersService;
    @Autowired
    private RedisService redisService;

    /**
     * @Author Mr.Jangni
     * @Description paramType：指定参数放在哪个地方
     * header：请求参数放置于Request Header，使用@RequestHeader获取
     * query：请求参数放置于请求地址，使用@RequestParam获取
     * path：（用于restful接口）-->请求参数的获取：@PathVariable
     * body：（不常用）
     * form（不常用）
     * name：参数名
     * dataType：参数类型
     * required：参数是否必须传
     * true | false
     * value：说明参数的意思
     * defaultValue：参数的默认值
     * @Date 2020/3/25 18:11
     * @Param [request, response]
     * @Return org.springframework.http.ResponseEntity<?>
     **/
    @ApiOperation(value = "用户退出")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = true, dataType = "string")
    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public ResponseEntity<?> exit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        String token = request.getHeader("token");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (!StringUtils.isEmpty(username)) {
            logger.info("delete token:{}", username);
            redisService.del(username);
        }
        return ResultJson.success();
    }

    /**
     * 查询全部
     *
     * @return
     */
    @ApiOperation(value = "用户列表")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody UserQuery userQuery) {
        logger.info("page={},size={},username={}", userQuery.getPage(), userQuery.getSize(), userQuery.getUsername());
        return ResultJson.success(usersService.usersList(userQuery));
    }

    @ApiOperation(value = "查询用户")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    public ResponseEntity<?> selectUser(@RequestBody @Pattern(regexp = "[0-9]{19}", message = "ID不正确！") Long id) {
        return ResultJson.success(usersService.selectUser(id));
    }


    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody @Validated UserSave user) {
        usersService.saveUser(user);
        return ResultJson.success();
    }

    @ApiOperation(value = "更新用户")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody @Validated UserUpdate user) {
        usersService.updateUser(user);
        return ResultJson.success();
    }


    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public ResponseEntity<?> delUser(@RequestBody @Pattern(regexp = "[0-9]{19}", message = "ID不正确！") Long id) {
        usersService.delUser(id);
        return ResultJson.success();
    }

}
