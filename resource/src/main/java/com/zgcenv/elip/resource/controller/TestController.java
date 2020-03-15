package com.zgcenv.elip.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ClassName TestController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/15 17:23
 * @Version 1.0
 **/
@RestController
public class TestController {
    @GetMapping("info")
    public String test(){
        return "resource";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
