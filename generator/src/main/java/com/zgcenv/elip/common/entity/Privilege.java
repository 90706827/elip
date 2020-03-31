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
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tu_privilege")
public class Privilege extends Model<Privilege> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限
     */
    @TableId(value = "privilege_id", type = IdType.AUTO)
    private Long privilegeId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String ename;

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


    public static final String PRIVILEGE_ID = "privilege_id";

    public static final String NAME = "name";

    public static final String ENAME = "ename";

    public static final String DESCRIPTION = "description";

    public static final String INSERT_TIME = "insert_time";

    public static final String INSERT_BY = "insert_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_BY = "update_by";

    @Override
    protected Serializable pkVal() {
        return this.privilegeId;
    }

}
