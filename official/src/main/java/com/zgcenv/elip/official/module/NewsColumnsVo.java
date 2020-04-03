package com.zgcenv.elip.official.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName NewsCloumnsVo
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/27 14:43
 * @Version 1.0
 **/
@Data
@ApiModel
public class NewsColumnsVo {

    @ApiModelProperty(value = "新闻id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "栏目id")
    private Long columnId;

    @ApiModelProperty(value = "栏目名称")
    private String columnTitle;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "头图")
    private String headPic;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "内容")
    private String context;

    @ApiModelProperty(value = "查看数")
    private Integer lookSum;

    @ApiModelProperty(value = "关注数")
    private Integer followSum;

    @ApiModelProperty(value = "关注数")
    private Integer sorting;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    @ApiModelProperty(value = "更新日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
