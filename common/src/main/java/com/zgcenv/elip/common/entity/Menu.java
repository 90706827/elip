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
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单表
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父类ID
     */
    @TableField("parentId")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField("menuName")
    private String menuName;

    /**
     * 菜单路径
     */
    @TableField("menuUrl")
    private String menuUrl;

    /**
     * 权限
     */
    private String perms;

    /**
     * 菜单排序
     */
    @TableField("menuOrder")
    private Integer menuOrder;

    /**
     * 1-开启；0-关闭
     */
    private Integer status;

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

    public static final String PARENTID = "parentId";

    public static final String MENUNAME = "menuName";

    public static final String MENUURL = "menuUrl";

    public static final String PERMS = "perms";

    public static final String MENUORDER = "menuOrder";

    public static final String STATUS = "status";

    public static final String INSERTTIME = "insertTime";

    public static final String UPDATETIME = "updateTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
