package com.zgcenv.elip.common.exception;

/**
 * @ClassName ValidateCodeException
 * @Description 验证码异常
 * @Author Mr.Jangni
 * @Date 2020/3/7 16:11
 * @Version 1.0
 **/
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message) {
        super(message);
    }
}