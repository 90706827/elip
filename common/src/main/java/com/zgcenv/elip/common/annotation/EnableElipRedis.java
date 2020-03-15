package com.zgcenv.elip.common.annotation;

import com.zgcenv.elip.common.configure.ElipRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableLettuceRedis
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 21:27
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ElipRedisConfigure.class)
public @interface EnableElipRedis {
}
