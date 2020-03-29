package com.zgcenv.elip.official.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.official.entity.Users;
import com.zgcenv.elip.official.query.UserSave;
import com.zgcenv.elip.official.query.UserQuery;
import com.zgcenv.elip.official.query.UserUpdate;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-24
 */
public interface UsersService extends IService<Users> {

    Users findByUsername(String username);

    Map<String,Object> usersList(UserQuery query);

    void saveUser(UserSave user);

    void updateUser(UserUpdate user);

    Users selectUser(Long id);

    void delUser(Long id);
}
