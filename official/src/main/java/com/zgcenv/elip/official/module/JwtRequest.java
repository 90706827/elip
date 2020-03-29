package com.zgcenv.elip.official.module;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName JwtRequest
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/10/30 10:14
 * @Version 1.0
 **/
@Data
public class JwtRequest implements Serializable {

    private String username;
    private String password;
    private String key;
    private String verCode;

}
