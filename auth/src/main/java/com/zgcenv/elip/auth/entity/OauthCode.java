package com.zgcenv.elip.auth.entity;

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
public class OauthCode extends Model<OauthCode> {

    private static final long serialVersionUID = 1L;

    private String code;

    private String authentication;


    public static final String CODE = "code";

    public static final String AUTHENTICATION = "authentication";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
