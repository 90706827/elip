package com.zgcenv.elip.official.config.jwt;

import com.zgcenv.elip.official.entity.Users;
import com.zgcenv.elip.official.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomUserDetailService
 * @Description 验证用户是否存在
 * @Author Mr.Jangni
 * @Date 2019/10/23 15:09
 * @Version 1.0
 **/
@Component
public class JwtUserDetailService implements UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(JwtUserDetailService.class);

    @Autowired
    private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("JwtUserDetailService");
        if (username == null || "".equals(username.trim())) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        // 从数据库中取出用户信息
        Users users = usersService.findByUsername(username);
        // 判断用户是否存在
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        return new User(users.getUsername(), users.getPassword(), grantedAuthorities);
    }
}