package com.zgcenv.elip.auth.service.impl;

import com.zgcenv.elip.common.entity.User;
import com.zgcenv.elip.common.model.AuthUser;
import com.zgcenv.elip.auth.service.UserService;
import com.zgcenv.elip.common.utils.ElipUtil;
import com.zgcenv.elip.common.utils.ParamsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserDetailService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 20:08
 * @Version 1.0
 **/
@Slf4j
@Service
public class ElipUserDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        HttpServletRequest httpServletRequest = ElipUtil.getHttpServletRequest();
        User user = userService.findByName(username);
        if (user != null) {
            String permissions = userService.findUserPermissions(user.getLoginName());
            boolean notLocked = false;
            if (StringUtils.equals("1", user.getStatus())) {
                notLocked = true;
            }
            String password = user.getPassword();
            String loginType = (String) httpServletRequest.getAttribute(ParamsConstant.LOGIN_TYPE);
            if (StringUtils.equals(loginType, ParamsConstant.SOCIAL_LOGIN)) {
                password = passwordEncoder.encode(ParamsConstant.SOCIAL_LOGIN_PASSWORD);
            }
            AuthUser authUser = new AuthUser(user.getLoginName(), password, true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtils.copyProperties(user, authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}