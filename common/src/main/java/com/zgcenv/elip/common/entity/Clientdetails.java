package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Clientdetails extends Model<Clientdetails> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "appId", type = IdType.ASSIGN_ID)
    private String appId;

    @TableField("resourceIds")
    private String resourceIds;

    @TableField("appSecret")
    private String appSecret;

    private String scope;

    @TableField("grantTypes")
    private String grantTypes;

    @TableField("redirectUrl")
    private String redirectUrl;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    @TableField("additionalInformation")
    private String additionalInformation;

    @TableField("autoApproveScopes")
    private String autoApproveScopes;


    public static final String APPID = "appId";

    public static final String RESOURCEIDS = "resourceIds";

    public static final String APPSECRET = "appSecret";

    public static final String SCOPE = "scope";

    public static final String GRANTTYPES = "grantTypes";

    public static final String REDIRECTURL = "redirectUrl";

    public static final String AUTHORITIES = "authorities";

    public static final String ACCESS_TOKEN_VALIDITY = "access_token_validity";

    public static final String REFRESH_TOKEN_VALIDITY = "refresh_token_validity";

    public static final String ADDITIONALINFORMATION = "additionalInformation";

    public static final String AUTOAPPROVESCOPES = "autoApproveScopes";

    @Override
    protected Serializable pkVal() {
        return this.appId;
    }

}
