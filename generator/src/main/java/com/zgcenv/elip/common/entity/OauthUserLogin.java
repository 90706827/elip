package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class OauthUserLogin extends Model<OauthUserLogin> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户名
     */
    @TableId(value = "USER_NAME", type = IdType.ASSIGN_ID)
    private String userName;

    /**
     * 第三方平台名称
     */
    @TableField("PROVIDER_NAME")
    private String providerName;

    /**
     * 第三方平台账户ID
     */
    @TableField("PROVIDER_USER_ID")
    private String providerUserId;

    /**
     * 第三方平台用户名
     */
    @TableField("PROVIDER_USER_NAME")
    private String providerUserName;

    /**
     * 第三方平台昵称
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * 第三方平台头像
     */
    @TableField("IMAGE_URL")
    private String imageUrl;

    /**
     * 地址
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


    public static final String USER_NAME = "USER_NAME";

    public static final String PROVIDER_NAME = "PROVIDER_NAME";

    public static final String PROVIDER_USER_ID = "PROVIDER_USER_ID";

    public static final String PROVIDER_USER_NAME = "PROVIDER_USER_NAME";

    public static final String NICK_NAME = "NICK_NAME";

    public static final String IMAGE_URL = "IMAGE_URL";

    public static final String LOCATION = "LOCATION";

    public static final String REMARK = "REMARK";

    @Override
    protected Serializable pkVal() {
        return this.userName;
    }

}
