package com.zgcenv.elip.official.query;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName New
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/27 13:36
 * @Version 1.0
 **/
@Data
public class NewUpdate {

    @Pattern(regexp = "[0-9]{19}", message = "ID不正确！")
    private Long id;
    @Pattern(regexp = "[0-9]{19}", message = "栏目ID不正确！")
    private Long columnId;
    @Size(min = 1, max = 50, message = "新闻标题不能为空，长度不能大于50个字符！")
    private String title;
    @Size(min = 0, max = 512, message = "新闻标题长度不能大于512个字符！")
    private String headPic;
    @Size(min = 0, max = 200, message = "新闻描述长度不能大于200个字符！")
    private String description;
    @Size(min = 1, max = 8000, message = "新闻描述长度不能大于8000个字符！")
    private String context;
    @Pattern(regexp = "[0-9]{0,11}", message = "查看数不正确！")
    private Integer lookSum;
    @Pattern(regexp = "[0-9]{0,11}", message = "关注数不正确！")
    private Integer followSum;
}
