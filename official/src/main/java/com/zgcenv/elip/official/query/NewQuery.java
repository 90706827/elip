package com.zgcenv.elip.official.query;

import lombok.Data;

/**
 * @ClassName NewQuery
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/27 13:34
 * @Version 1.0
 **/
@Data
public class NewQuery extends PageBase {
    private Long columnId;
    private String title;
}
