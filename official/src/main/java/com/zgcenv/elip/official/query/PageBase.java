package com.zgcenv.elip.official.query;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @ClassName PageBase
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/12/3 22:04
 * @Version 1.0
 **/
@Data
public class PageBase {

    @Pattern(regexp = "[1-9]{1,3}",message = "页数不正确！")
    private Integer page;

    @Pattern(regexp = "[1-9]{1,3}",message = "每页条数不正确！")
    private Integer size;
}
