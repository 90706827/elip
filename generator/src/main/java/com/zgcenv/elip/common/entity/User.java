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
@TableName("tu_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String nickname;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册手机号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * token
     */
    private String token;

    /**
     * 头像
     */
    private String photo;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 状态：0-待激活，1-正常，2-冻结
     */
    private String status;

    /**
     * 性别：1-男，2-女
     */
    private Integer gender;

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


    public static final String USER_ID = "user_id";

    public static final String NICKNAME = "nickname";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String TOKEN = "token";

    public static final String PHOTO = "photo";

    public static final String SALT = "salt";

    public static final String STATUS = "status";

    public static final String GENDER = "gender";

    public static final String INSERT_TIME = "insert_time";

    public static final String INSERT_BY = "insert_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_BY = "update_by";

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
