package com.zgcenv.elip.common.annotation;

import com.zgcenv.elip.common.configure.ElipCloudApplicationConfigure;
import com.zgcenv.elip.common.configure.ElipPasswordConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableElipPasswordConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/11 23:43
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ElipPasswordConfigure.class)
public @interface EnableElipPasswordConfigure {
}
