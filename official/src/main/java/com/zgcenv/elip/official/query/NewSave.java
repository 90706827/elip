package com.zgcenv.elip.official.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @ClassName NewSave
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/29 23:44
 * @Version 1.0
 **/
@Data
public class NewSave {
    @NotNull(message = "栏目ID不正确！")
    @Positive(message = "栏目ID不正确！")
    private Long columnId;
    @Length(min = 1, max = 50, message = "新闻标题不能为空，长度不能大于50个字符！")
    private String title;
    @Length(min = 0, max = 512, message = "新闻标题长度不能大于512个字符！")
    private String headPic;
    @Length(min = 0, max = 200, message = "新闻描述长度不能大于200个字符！")
    private String description;
    @Length(min = 1, max = 8000, message = "新闻描述长度不能大于8000个字符！")
    private String context;
    @NotNull(message = "查看数不正确！")
    @PositiveOrZero(message = "查看数不正确！")
    private int lookSum;
    @NotNull(message = "关注数不正确！")
    @PositiveOrZero(message = "关注数不正确！")
    private int followSum;
    @NotNull(message = "排序不正确！")
    private Integer sorting;
}
