package com.zgcenv.elip.official.query;

import lombok.Data;

/**
 * @ClassName ColumnQuery
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/26 9:00
 * @Version 1.0
 **/
@Data
public class ColumnQuery extends PageBase {
    private String title;
    private Long parentId;
}
