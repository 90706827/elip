package com.zgcenv.elip.common.annotation;

import com.zgcenv.elip.common.configure.ElipServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableElipServerProtect
 * @Description 服务防护
 * @Author Mr.Jangni
 * @Date 2020/3/5 22:40
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ElipServerProtectConfigure.class)
public @interface EnableElipServerProtect {
}
