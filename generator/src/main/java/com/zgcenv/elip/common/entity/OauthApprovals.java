package com.zgcenv.elip.common.entity;

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
public class OauthApprovals extends Model<OauthApprovals> {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private String userId;

    @TableField("clientId")
    private String clientId;

    private String scope;

    private String status;

    @TableField("expiresAt")
    private LocalDateTime expiresAt;

    @TableField("lastModifiedAt")
    private LocalDateTime lastModifiedAt;


    public static final String USERID = "userId";

    public static final String CLIENTID = "clientId";

    public static final String SCOPE = "scope";

    public static final String STATUS = "status";

    public static final String EXPIRESAT = "expiresAt";

    public static final String LASTMODIFIEDAT = "lastModifiedAt";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
