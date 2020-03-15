package com.zgcenv.elip.auth.configure;

import com.zgcenv.elip.auth.handler.ElipWebResponseExceptionTranslator;
import com.zgcenv.elip.auth.service.impl.ElipUserDetailsService;
import com.zgcenv.elip.auth.service.impl.RedisClientDetailsService;
import com.zgcenv.elip.common.utils.AuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.UUID;

/**
 * @ClassName AuthorizationServerConfigure
 * @Description 配置授权中心信息
 * @Author Mr.Jangni
 * @Date 2020/3/5 20:58
 * @Version 1.0
 **/
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private ElipUserDetailsService elipUserDetailsService;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;
    @Autowired
    private AuthProperties authProperties;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ElipWebResponseExceptionTranslator elipWebResponseExceptionTranslator;

    /**
     * @Author Mr.Jangni
     * @Description 定义客户端详细信息服务的配置程序。可以初始化客户端详细信息
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        log.info("AuthorizationServerConfigure-ClientDetailsServiceConfigurer");
        clients.withClientDetails(redisClientDetailsService);
    }

    /**
     * @Author Mr.Jangni
     * @Description 定义令牌端点上的安全约束
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        log.info("AuthorizationServerConfigure-security");
        super.configure(security);
    }

    /**
     * @Author Mr.Jangni
     * @Description 定义授权和令牌端点以及令牌服务
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        log.info("AuthorizationServerConfigure-endpoints");
        endpoints.tokenStore(tokenStore())
                .userDetailsService(elipUserDetailsService)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(elipWebResponseExceptionTranslator);
        if (authProperties.getEnableJwt()) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
        }
    }

    @Bean
    public TokenStore tokenStore() {
        log.info("AuthorizationServerConfigure-tokenStore");
        if (authProperties.getEnableJwt()) {
            return new JwtTokenStore(jwtAccessTokenConverter());
        } else {
            RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
            // 解决每次生成的 token都一样的问题
            redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
            return redisTokenStore;
        }
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        log.info("AuthorizationServerConfigure-jwtAccessTokenConverter");
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) accessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(elipUserDetailsService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        accessTokenConverter.setSigningKey(authProperties.getJwtAccessKey());
        return accessTokenConverter;
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        log.info("AuthorizationServerConfigure-defaultTokenServices");

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(redisClientDetailsService);

        return tokenServices;
    }

    @Bean
    public ResourceOwnerPasswordTokenGranter resourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {

        log.info("AuthorizationServerConfigure-resourceOwnerPasswordTokenGranter");
        DefaultTokenServices defaultTokenServices = defaultTokenServices();
        if (authProperties.getEnableJwt()) {
            defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        }
        return new ResourceOwnerPasswordTokenGranter(authenticationManager, defaultTokenServices, redisClientDetailsService, oAuth2RequestFactory);
    }

    @Bean
    public DefaultOAuth2RequestFactory oAuth2RequestFactory() {
        log.info("AuthorizationServerConfigure-oAuth2RequestFactory");
        return new DefaultOAuth2RequestFactory(redisClientDetailsService);
    }

}
