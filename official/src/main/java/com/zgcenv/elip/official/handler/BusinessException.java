package com.zgcenv.elip.official.handler;

import com.zgcenv.elip.official.utils.RespCode;

/**
 * @ClassName BusinessException
 * @Description 自定义业务异常
 * @Author Mr.Jangni
 * @Date 2020/3/29 23:17
 * @Version 1.0
 **/
public class BusinessException extends Exception {

    private RespCode respCode;

    public BusinessException() {
        super();
    }

    public BusinessException(RespCode respCode) {
        super();
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return this.respCode;
    }
}
