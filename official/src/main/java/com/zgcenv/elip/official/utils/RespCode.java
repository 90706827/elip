package com.zgcenv.elip.official.utils;

import io.swagger.annotations.ApiModel;

/**
 * @ClassName RespCode
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/29 19:04
 * @Version 1.0
 **/
@ApiModel
public enum RespCode {
    /**
     * 没有访问权限
     **/
    SYSTEM_ERROR(500, "系统繁忙，请稍后重试！"),
    NOT_REQUEST(401, "没有访问权限！"),
    REQUEST_SUCCESS(200, "成功！"),
    REQUEST_FAILED(500, "失败！"),
    VER_CODE_EXPIRE(700, "验证码失效！"),
    VER_CODE_ERROR(701, "验证码不正确！"),
    USER_NOT_FIND(702, "用户不存在！"),
    USERNAME_PASSWORD_ERROR(703, "用户名或密码不正确！"),
    NOT_IMAGE(704,"请上传图片！"),
    NOT_FIND_NEWS(705,"新闻不存在！"),
    UPLOAD_IMAGE_FAILED(706,"上传图片失败！"),
    FIND_NEWS(707,"删除菜单新闻后再操作!"),
    FIND_COLUMNS(708,"删除子菜单后再操作!"),
    NOT_FIND_COLUMNS(709,"没有查到所属菜单!");

    private Integer code;
    private String message;

    RespCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
