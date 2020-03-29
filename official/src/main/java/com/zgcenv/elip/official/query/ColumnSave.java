package com.zgcenv.elip.official.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName Column
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/26 21:21
 * @Version 1.0
 **/
@Data
public class ColumnSave {
    @Size(min = 1,max = 50,message = "栏目名称不能为空，长度不能大于50个字符！")
    private String title;
    @Size(min = 0,max = 2000,message = "栏目描述长度不能大于2000个字符！")
    private String description;
}
