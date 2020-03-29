package com.zgcenv.elip.official.config.jwt;

import com.zgcenv.elip.official.service.RedisService;
import io.jsonwebtoken.JwtException;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @ClassName JwtRequestFilter
 * @Description 拦截器
 * @Author Mr.Jangni
 * @Date 2019/10/30 10:15
 * @Version 1.0
 **/

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException, JwtException {
//        printHeaders(request);
        logger.info("JwtRequestFilter Request path:{}", request.getServletPath());
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            boolean isExists = redisService.exists(username);
            if (!isExists || StringUtils.isBlank(username) || SecurityContextHolder.getContext().getAuthentication() != null) {
                logger.info("token 验证不存在");
                throw new JwtException("Token验证不存在！");
            } else {
                UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    logger.info("oauth ok, username:{},token:{}", username, token);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    logger.info("Token验证非法!");
                    throw new JwtException("Token验证非法！");
                }
            }
        }
        chain.doFilter(request, response);
    }


    private void printHeaders(HttpServletRequest request) {
        Enumeration<String> list = request.getHeaderNames();
        while (list.hasMoreElements()) {
            String key = (String) list.nextElement();
            logger.info(key + ":" + request.getHeader(key));
        }
    }

}
