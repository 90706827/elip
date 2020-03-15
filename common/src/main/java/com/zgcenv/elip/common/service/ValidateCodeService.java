package com.zgcenv.elip.common.service;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.zgcenv.elip.common.exception.ValidateCodeException;
import com.zgcenv.elip.common.service.RedisService;
import com.zgcenv.elip.common.utils.CaptchaConstant;
import com.zgcenv.elip.common.utils.ParamsConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 16:12
 * @Version 1.0
 **/
public class ValidateCodeService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private CaptchaConstant captchaConstant;


    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        String key = request.getParameter(ParamsConstant.VALIDATE_CODE_KEY);
        if (StringUtils.isBlank(key)) {
            throw new ValidateCodeException("验证码key不能为空");
        }
        setHeader(response, captchaConstant.getType());

        Captcha captcha = createCaptcha(captchaConstant);
        redisService.set(ParamsConstant.CODE_PREFIX + key, StringUtils.lowerCase(captcha.text()), captchaConstant.getTime());
        captcha.out(response.getOutputStream());
    }

    public void check(String key, String value) throws ValidateCodeException {

        Object codeInRedis = redisService.get(ParamsConstant.CODE_PREFIX + key);

        if (StringUtils.isBlank(value)) {
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new ValidateCodeException("验证码不正确");
        }
    }

    private Captcha createCaptcha(CaptchaConstant code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), ParamsConstant.IMAGE_GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, ParamsConstant.IMAGE_GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
