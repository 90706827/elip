package com.zgcenv.elip.official.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_columns")
public class Columns extends Model<Columns> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父类ID
     */
    private Long parentId;

    /**
     * 栏目标题
     */
    private String title;

    /**
     * 栏目描述
     */
    private String description;

    /**
     * 栏目排序
     */
    private Integer sorting;

    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH-mm-ss")
    private Date insertTime;

    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH-mm-ss")
    private Date updateTime;


    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String TITLE = "title";

    public static final String DESCRIPTION = "description";

    public static final String SORTING = "sorting";

    public static final String INSERT_TIME = "insert_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
