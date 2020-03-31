package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_news")
public class News extends Model<News> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 栏目id
     */
    private Long columnId;

    /**
     * 标题
     */
    private String title;

    /**
     * 头图
     */
    private String headPic;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容
     */
    private String context;

    /**
     * 查看数
     */
    private Integer lookSum;

    /**
     * 关注数
     */
    private Integer followSum;

    /**
     * 创建日期
     */
    private LocalDateTime insertTime;

    /**
     * 更新日期
     */
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String COLUMN_ID = "column_id";

    public static final String TITLE = "title";

    public static final String HEAD_PIC = "head_pic";

    public static final String DESCRIPTION = "description";

    public static final String CONTEXT = "context";

    public static final String LOOK_SUM = "look_sum";

    public static final String FOLLOW_SUM = "follow_sum";

    public static final String INSERT_TIME = "insert_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
