package com.zgcenv.elip.auth.controller;

import com.zgcenv.elip.common.exception.ValidateCodeException;
import com.zgcenv.elip.common.service.ValidateCodeService;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SecurityController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 16:00
 * @Version 1.0
 **/
@RestController
public class SecurityController {
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }
}
