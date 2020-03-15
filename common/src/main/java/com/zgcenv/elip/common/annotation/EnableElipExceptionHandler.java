package com.zgcenv.elip.common.annotation;

import com.zgcenv.elip.common.configure.ElipExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableElipExceptionHandler
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 22:17
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ElipExceptionConfigure.class)
public @interface EnableElipExceptionHandler {

}
