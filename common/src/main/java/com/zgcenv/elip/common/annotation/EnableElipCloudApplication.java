package com.zgcenv.elip.common.annotation;

import com.zgcenv.elip.common.configure.ElipCloudApplicationConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableElipCloudApplication
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 21:19
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ElipCloudApplicationConfigure.class)
public @interface EnableElipCloudApplication {

}
