package com.zgcenv.elip.official.query;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @ClassName UserQuery
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/12/3 22:06
 * @Version 1.0
 **/
@Data
public class UserQuery extends PageBase {
    private String username;
}
