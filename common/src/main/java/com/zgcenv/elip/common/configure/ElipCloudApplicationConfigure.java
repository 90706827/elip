package com.zgcenv.elip.common.configure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Nonnull;

/**
 * @ClassName ElipCloudApplicationConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 21:21
 * @Version 1.0
 **/
public class ElipCloudApplicationConfigure implements ImportSelector {
    @Override
    @SuppressWarnings("all")
    public String[] selectImports(@Nonnull AnnotationMetadata annotationMetadata) {
        return new String[]{
                ElipExceptionConfigure.class.getName(),
                ElipOauthFeignConfigure.class.getName(),
                ElipServerProtectConfigure.class.getName()
        };
    }
}
