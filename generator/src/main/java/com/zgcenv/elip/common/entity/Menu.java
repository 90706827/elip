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
@TableName("tu_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 视图
     */
    private String component;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 隐藏型菜单
     */
    private String hidden;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：0-不可用，1-可用
     */
    private Integer status;

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


    public static final String MENU_ID = "menu_id";

    public static final String PARENT_ID = "parent_id";

    public static final String PATH = "path";

    public static final String COMPONENT = "component";

    public static final String REDIRECT = "redirect";

    public static final String HIDDEN = "hidden";

    public static final String NAME = "name";

    public static final String TITLE = "title";

    public static final String ICON = "icon";

    public static final String SORT = "sort";

    public static final String DESCRIPTION = "description";

    public static final String STATUS = "status";

    public static final String INSERT_TIME = "insert_time";

    public static final String INSERT_BY = "insert_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_BY = "update_by";

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
