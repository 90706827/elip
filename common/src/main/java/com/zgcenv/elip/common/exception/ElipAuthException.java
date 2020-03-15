package com.zgcenv.elip.common.exception;

/**
 * @ClassName FebsAuthException
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 16:06
 * @Version 1.0
 **/
public class ElipAuthException extends Exception {

    private static final long serialVersionUID = -6916154462432027437L;

    public ElipAuthException(String message) {
        super(message);
    }
}