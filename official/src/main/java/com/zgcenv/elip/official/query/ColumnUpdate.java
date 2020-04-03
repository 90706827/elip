package com.zgcenv.elip.official.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * @ClassName ColumnUpdate
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/29 23:41
 * @Version 1.0
 **/
@Data
public class ColumnUpdate {

    @NotNull(message = "ID不正确！")
    @Positive(message = "ID不正确！")
    private Long Id;
    @NotNull(message = "ID不正确！")
    @Positive(message = "ID不正确！")
    private Long parentId;
    @Size(min = 1,max = 50,message = "栏目名称不能为空，长度不能大于50个字符！")
    private String title;
    @NotNull(message = "排序不正确！")
    private Integer sorting;
    @Size(min = 0,max = 2000,message = "栏目描述长度不能大于2000个字符！")
    private String description;
}
