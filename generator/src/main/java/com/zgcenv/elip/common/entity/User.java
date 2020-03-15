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
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名称
     */
    @TableField("loginName")
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 昵称
     */
    @TableField("displayName")
    private String displayName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * token
     */
    private String token;

    /**
     * 头像
     */
    @TableField("headImg")
    private String headImg;

    /**
     * 0-待激活；1-待认证；2-已激活；3-已认证；4-已注销；5-已冻结；9-未通过
     */
    private String status;

    /**
     * 新增日期
     */
    @TableField("insertTime")
    private LocalDateTime insertTime;

    /**
     * 修改日期
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String LOGINNAME = "loginName";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String DISPLAYNAME = "displayName";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String TOKEN = "token";

    public static final String HEADIMG = "headImg";

    public static final String STATUS = "status";

    public static final String INSERTTIME = "insertTime";

    public static final String UPDATETIME = "updateTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
