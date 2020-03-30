package com.zgcenv.elip.official.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @ClassName PageBase
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/12/3 22:04
 * @Version 1.0
 **/
@Data
public class PageBase {

    @NotNull(message = "页数不正确！")
    @PositiveOrZero(message = "页数不正确！")
    private Integer page;

    @NotNull(message = "每页条数不正确！")
    @PositiveOrZero(message = "每页条数不正确！")
    private Integer size;
}
