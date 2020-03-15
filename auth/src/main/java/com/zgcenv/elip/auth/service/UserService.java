package com.zgcenv.elip.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.common.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
public interface UserService extends IService<User> {

    User findByName(String username);

    String findUserPermissions(String username);
}
