package com.zgcenv.elip.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
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
public class OauthAccessToken extends Model<OauthAccessToken> {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private Blob token;

    @TableId(value = "authentication_id", type = IdType.ASSIGN_ID)
    private String authenticationId;

    private String userName;

    private String clientId;

    private Blob authentication;

    private String refreshToken;

    private String originSecret;


    public static final String TOKEN_ID = "token_id";

    public static final String TOKEN = "token";

    public static final String AUTHENTICATION_ID = "authentication_id";

    public static final String USER_NAME = "user_name";

    public static final String CLIENT_ID = "client_id";

    public static final String AUTHENTICATION = "authentication";

    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String ORIGIN_SECRET = "origin_secret";

    @Override
    protected Serializable pkVal() {
        return this.authenticationId;
    }

}
