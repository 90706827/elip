package com.zgcenv.elip.official.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zgcenv.elip.official.config.jwt.JwtRequestInterceptor;
import com.zgcenv.elip.official.config.jwt.JwtTokenUtil;
import com.zgcenv.elip.official.config.jwt.JwtUserDetailService;
import com.zgcenv.elip.official.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Description Web Mvc 配置
 * @Author Mr.Jangni
 * @Date 2019/10/22 0:22
 * @Version 1.0
 **/

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtRequestInterceptor(jwtUserDetailService, jwtTokenUtil, redisService))
                .excludePathPatterns("/web/**");
    }

    /**
     * @Author Mr.Jangni
     * @Description swagger 访问
     * @Date 2020/3/26 9:25
     * @Param [registry]
     * @Return void
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * @Author Mr.Jangni
     * @Description 设置全局跨域访问
     * @Date 2019/11/2 12:31
     * @Param [registry]
     * @Return void
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9090", "http://www.zgcenv.com")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("token")
                .maxAge(1800)
                .allowCredentials(false);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson.setObjectMapper(objectMapper);
        converters.add(jackson);
    }

}
