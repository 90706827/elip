package com.zgcenv.elip.official.config.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName JwtTokenUtil
 * @Description 生成 token
 * @Author Mr.Jangni
 * @Date 2019/10/30 10:08
 * @Version 1.0
 **/
@Component
public class JwtTokenUtil implements Serializable {

    /**
     * @Author Mr.Jangni
     * @Description 单位毫秒
     * @Date 2019/11/18 10:12
     **/
    public static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * @Author Mr.Jangni
     * @Description 根据token获取username
     * @Date 2019/10/30 10:10
     * @Param
     * @Return
     **/
    public String getUsernameFromToken(String token) throws JwtException {
        String username = "";
        try {
            username = getClaimFromToken(token, Claims::getSubject);
        } catch (ExpiredJwtException exception) {
            throw new JwtException("token失效！");
        }
        return username;
    }

    /**
     * @Author Mr.Jangni
     * @Description 获取token的过期时间
     * @Date 2019/10/30 10:10
     * @Param [token]
     * @Return java.util.Date
     **/
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * @Author Mr.Jangni
     * @Description 获取userId
     * @Date 2019/10/30 10:10
     * @Param [token]
     * @Return java.util.Date
     **/
    public Long getIdFromToken(String token) {
        return Long.valueOf(getClaimFromToken(token, Claims::getId));
    }

    /**
     * @Author Mr.Jangni
     * @Description 解析 TOKEN
     * @Date 2019/10/30 10:10
     * @Param [token, claimsResolver]
     * @Return T
     **/
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);

    }

    /**
     * @Author Mr.Jangni
     * @Description 验证Token是否过期
     * @Date 2019/10/30 10:10
     * @Param [token]
     * @Return java.lang.Boolean
     **/
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * @Author Mr.Jangni
     * @Description 生成Token
     * @Date 2019/10/30 10:11
     * @Param [userDetails]
     * @Return java.lang.String
     **/
    public String generateToken(UserDetails userDetails, Long userId) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                //签发者
                .setIssuer("www.baidu.com")
                //签发日期
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setId(String.valueOf(userId))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @Author Mr.Jangni
     * @Description 验证 token
     * @Date 2019/10/30 10:11
     * @Param [token, userDetails]
     * @Return java.lang.Boolean
     **/
    public Boolean validateToken(String token, UserDetails userDetails) throws JwtException {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * @Author Mr.Jangni
     * @Description 验证 token
     * @Date 2019/10/30 10:11
     * @Param [token, userDetails]
     * @Return java.lang.Boolean
     **/
    public Boolean validateToken(String token, String userName) throws JwtException {
        return (getUsernameFromToken(token).equals(userName) && !isTokenExpired(token));
    }

}
