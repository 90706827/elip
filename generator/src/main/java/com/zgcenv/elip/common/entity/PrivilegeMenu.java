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
@TableName("tu_privilege_menu")
public class PrivilegeMenu extends Model<PrivilegeMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单
     */
    private Long menuId;

    /**
     * 权限
     */
    private Long privilegeId;

    /**
     * 插入时间
     */
    private LocalDateTime insertTime;

    /**
     * 插入用户
     */
    private Long insertBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新用户
     */
    private Long updateBy;


    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String PRIVILEGE_ID = "privilege_id";

    public static final String INSERT_TIME = "insert_time";

    public static final String INSERT_BY = "insert_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_BY = "update_by";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
