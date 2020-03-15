package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色表
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名
     */
    @TableField("roleName")
    private String roleName;

    /**
     * 新增角色
     */
    @TableField("insertTime")
    private LocalDateTime insertTime;

    /**
     * 更新角色
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String ROLENAME = "roleName";

    public static final String INSERTTIME = "insertTime";

    public static final String UPDATETIME = "updateTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
