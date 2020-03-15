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
public class OauthCode extends Model<OauthCode> {

    private static final long serialVersionUID = 1L;

    private String code;

    private Blob authentication;


    public static final String CODE = "code";

    public static final String AUTHENTICATION = "authentication";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
