package com.zgcenv.elip.common.utils;

/**
 * @ClassName ParamsConstant
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 21:17
 * @Version 1.0
 **/
public class ParamsConstant {


    /**
     * gif类型
     */
    public static final String IMAGE_GIF = "gif";
    /**
     * png类型
     */
    public static final String IMAGE_PNG = "png";

    /**
     * 验证码 key
     */
    public static final String VALIDATE_CODE_KEY = "key";

    /**
     * 验证码 code
     */
    public static final String VALIDATE_CODE_CODE = "code";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "elip.captcha.";

    /**
     * 认证类型参数 key
     */
    public static final String GRANT_TYPE = "grant_type";
    /**
     * 登录类型
     */
    public static final String LOGIN_TYPE = "login_type";

    public static final String SOCIAL_LOGIN = "social_login";

    public static final String SOCIAL_LOGIN_PASSWORD = "social_login_password";

    /**
     * Gateway请求头TOKEN名称（不要有空格）
     */
    public static final String GATEWAY_TOKEN_HEADER = "GatewayToken";
    /**
     * Gateway请求头TOKEN值
     */
    public static final String GATEWAY_TOKEN_VALUE = "elip:gateway:123456";
    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    public static final String OAUTH2_TOKEN_TYPE = "bearer";

    /**
     * 通配符
     **/
    public static final String URL_ALL = "/**";
    public static final String URL_OAUTH_ALL = "/oauth/**";
    public static final String OAUTH_TOKEN = "/oauth/token";


    /**
     * 刷新模式
     */
    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    /**
     * 授权码模式
     */
    public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
    /**
     * 客户端模式
     */
    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    /**
     * 密码模式
     */
    public static final String GRANT_TYPE_PASSWORD = "password";
    /**
     * 简化模式
     */
    public static final String GRANT_TYPE_IMPLICIT = "implicit";
}
