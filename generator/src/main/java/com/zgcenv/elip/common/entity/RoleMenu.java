package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_menu")
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色菜单表
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色表
     */
    @TableField("roleId")
    private Long roleId;

    /**
     * 菜单表
     */
    @TableField("menuId")
    private Long menuId;

    /**
     * 新增时间
     */
    @TableField("insertTime")
    private LocalDateTime insertTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String ROLEID = "roleId";

    public static final String MENUID = "menuId";

    public static final String INSERTTIME = "insertTime";

    public static final String UPDATETIME = "updateTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
