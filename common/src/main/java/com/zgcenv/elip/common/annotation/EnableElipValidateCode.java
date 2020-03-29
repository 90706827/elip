package com.zgcenv.elip.common.annotation;

//import com.zgcenv.elip.common.filter.ValidateCodeFilter;
import com.zgcenv.elip.common.service.ValidateCodeService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableElipValidateCode
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/11 13:23
 * @Version 1.0
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ValidateCodeService.class})
public @interface EnableElipValidateCode {

}
