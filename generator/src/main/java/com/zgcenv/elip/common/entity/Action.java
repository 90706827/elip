package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tu_action")
public class Action extends Model<Action> {

    private static final long serialVersionUID = 1L;

    /**
     * 操作
     */
    @TableId(value = "action_id", type = IdType.AUTO)
    private Long actionId;

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 操作url
     */
    private String url;

    /**
     * 有效状态：1-有效，0-无效
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    private LocalDateTime insertTime;

    /**
     * 插入用户
     */
    private Long insertBy;

    private LocalDateTime updateTime;

    /**
     * 更新用户
     */
    private Long updateBy;


    public static final String ACTION_ID = "action_id";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String URL = "url";

    public static final String STATUS = "status";

    public static final String DESCRIPTION = "description";

    public static final String INSERT_TIME = "insert_time";

    public static final String INSERT_BY = "insert_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_BY = "update_by";

    @Override
    protected Serializable pkVal() {
        return this.actionId;
    }

}
