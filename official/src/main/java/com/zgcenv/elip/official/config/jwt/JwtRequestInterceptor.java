package com.zgcenv.elip.official.config.jwt;

import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.service.RedisService;
import com.zgcenv.elip.official.utils.RespCode;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName JwtRequestInterceptor
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/2/14 10:16
 * @Version 1.0
 **/
public class JwtRequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestInterceptor.class);

    private JwtUserDetailService jwtUserDetailService;
    private JwtTokenUtil jwtTokenUtil;
    private RedisService redisService;

    public JwtRequestInterceptor(JwtUserDetailService jwtUserDetailService, JwtTokenUtil jwtTokenUtil, RedisService redisService) {
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisService = redisService;
    }

    //前置功能，  开始到结束，两个点减法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        logger.info(request.getRequestURI() + ",请求到达");

        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            String username = "";
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (MalformedJwtException exception) {
                logger.info("token 验证码错误");
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().print(ResultJson.toJson(RespCode.NOT_REQUEST));
                return false;
            }
            boolean isExists = redisService.exists(username);
            if (!isExists || StringUtils.isBlank(username)) {
                logger.info("token 验证不存在");
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().print(ResultJson.toJson(RespCode.NOT_REQUEST));
                return false;
            } else {
                if (jwtTokenUtil.validateToken(token, username)) {
                    logger.info("权限认证通过");
                } else {
                    logger.info("Token验证非法");
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().print(ResultJson.toJson(RespCode.NOT_REQUEST));
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long start = (long) request.getAttribute("start");
        long end = System.currentTimeMillis();
        long spendTime = end - start;
        if (spendTime > 2000) {
            logger.warn("方法耗时严重，请及时处理，耗时：" + spendTime);
        } else {
            logger.info("方法耗时" + spendTime + "毫秒，正常");
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info(" 拦截器 afterCompletion");
    }
}
