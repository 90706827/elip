package com.zgcenv.elip.common.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName QueryRequest
 * @Description 分页
 * @Author Mr.Jangni
 * @Date 2020/3/8 21:46
 * @Version 1.0
 **/
@Data
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;
    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
