package com.zgcenv.elip.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.auth.dao.MenuDao;
import com.zgcenv.elip.auth.dao.UserDao;
import com.zgcenv.elip.common.entity.Menu;
import com.zgcenv.elip.common.entity.User;
import com.zgcenv.elip.auth.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private MenuDao menuDao;

    @Override
    public User findByName(String username) {
        return userDao.selectOne(new QueryWrapper<User>().lambda().eq(User::getLoginName, username));
    }

    @Override
    public String findUserPermissions(String username) {
            List<Menu> userPermissions = menuDao.findUserPermissions(username);
            return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
