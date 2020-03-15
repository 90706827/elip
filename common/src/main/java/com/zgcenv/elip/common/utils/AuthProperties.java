package com.zgcenv.elip.common.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthProperties
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 17:29
 * @Version 1.0
 **/
@Data
@Component
public class AuthProperties {
    /**
     * 免认证访问路径
     */
    private String anonUrl = "/actuator/**,/captcha,/social/**,/oauth/**";
    /**
     * 验证码配置
     */
    private CaptchaConstant code = new CaptchaConstant();
    /**
     * JWT加签密钥
     */
    private String jwtAccessKey = "elip";
    /**
     * 是否使用 JWT令牌
     */
    private Boolean enableJwt = false;

    /**
     * 社交登录所使用的 Client
     */
    private String socialLoginClientId = "app";
}
