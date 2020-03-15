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
@TableName("sys_param")
public class Param extends Model<Param> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统参数表
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * key
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 1-启用；0-关闭
     */
    @TableField("isValid")
    private Integer isValid;

    /**
     * 描述
     */
    private String ext;

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

    public static final String TYPE = "type";

    public static final String KEY = "key";

    public static final String VALUE = "value";

    public static final String ISVALID = "isValid";

    public static final String EXT = "ext";

    public static final String INSERTTIME = "insertTime";

    public static final String UPDATETIME = "updateTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
