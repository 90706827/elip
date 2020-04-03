package com.zgcenv.elip.official.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CloumnSelected
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/4/3 10:48
 * @Version 1.0
 **/
@Data
@ApiModel(value = "选择器")
public class SelectedColumnVo {

    @ApiModelProperty(value = "栏目id")
    private Long id;

    @ApiModelProperty(value = "栏目标题")
    private String title;

    @ApiModelProperty(value = "子栏目集合")
    private List<SelectedColumnVo> list;
}
