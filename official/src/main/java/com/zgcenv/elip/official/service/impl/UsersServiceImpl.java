package com.zgcenv.elip.official.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.official.dao.UsersDao;
import com.zgcenv.elip.official.entity.Users;
import com.zgcenv.elip.official.query.UserQuery;
import com.zgcenv.elip.official.query.UserSave;
import com.zgcenv.elip.official.query.UserUpdate;
import com.zgcenv.elip.official.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-24
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

    @Resource
    private UsersDao usersDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users findByUsername(String username) {
        return usersDao.selectOne(new QueryWrapper<Users>().eq("username", username));
    }

    @Override
    public Map<String, Object> usersList(UserQuery query) {
        Map<String, Object> map = new HashMap<>(50);
        IPage<Users> page = new Page<>(query.getPage(), query.getSize());
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq(!StringUtils.isEmpty(query.getUsername()), "username", query.getUsername());
        wrapper.orderByAsc("id");
        IPage<Users> iPage = baseMapper.selectPage(page, wrapper);
        map.put("list", iPage.getRecords());
        map.put("page", query.getPage());
        map.put("total", iPage.getTotal());
        map.put("size", query.getSize());
        return map;
    }

    @Override
    public void saveUser(UserSave user) {
        Users users = new Users();
        users.setUsername(user.getUsername());
        users.setPassword(passwordEncoder.encode(user.getPassword()));
        users.setEmail(user.getEmail());
        users.setPhone(user.getPhone());
        users.setInsertTime(new Date());
        users.setUpdateTime(new Date());
        baseMapper.insert(users);
    }

    @Override
    public void updateUser(UserUpdate user) {
        Users users = new Users();
        users.setId(user.getId());
        users.setUsername(user.getUsername());
        users.setEmail(user.getEmail());
        users.setPhone(user.getPhone());
        users.setInsertTime(new Date());
        users.setUpdateTime(new Date());
        baseMapper.updateById(users);

    }

    @Override
    public Users selectUser(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void delUser(Long id) {
        baseMapper.deleteById(id);
    }
}
