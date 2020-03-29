package com.zgcenv.elip.official.handler;

import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.utils.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @ClassName GlobalExceptionHandler
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/29 23:06
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Author Mr.Jangni
     * @Description 验证框架异常捕获
     * @Date 2020/3/29 23:26
     * @Param [exception]
     * @Return java.lang.Object
     **/
    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        } else {
            msg = "系统繁忙，请稍后重试...";
        }
        return ResultJson.failed(msg);
    }

    /**
     * @Author Mr.Jangni
     * @Description 业务异常
     * @Date 2020/3/29 23:26
     * @Param [exception]
     * @Return java.lang.Object
     **/
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Object allBusinessExceptionHandler(BusinessException exception) {
        return ResultJson.failed(exception.getRespCode());
    }

    /**
     * @Author Mr.Jangni
     * @Description 未知的异常捕获处理
     * @Date 2020/3/29 23:16
     * @Param [request, exception]
     * @Return java.lang.Object
     **/
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object allUnknowExceptionHandler(HttpServletRequest request, Exception exception) {
        String error = logError(request, exception);
        //可以发送邮件通知开发
        return ResultJson.failed(RespCode.SYSTEM_ERROR);
    }

    private String logError(HttpServletRequest request, Exception exception) {
        LOG.error("发生未知异常:", exception);
        StringWriter sw = new StringWriter();
        sw.append(String.format("Date:{%s};\n", new Date()));
        sw.append(String.format("url:{%s}产生错误;\n", request.getRequestURI()));
        sw.append(String.format("请求IP:{%s};\n", request.getRemoteAddr()));
        sw.append(String.format("type:{%s};\n", request.getMethod()));
        sw.append(String.format("请求参数:{%s};\n", JSONObject.wrap(request.getParameterMap())));
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}