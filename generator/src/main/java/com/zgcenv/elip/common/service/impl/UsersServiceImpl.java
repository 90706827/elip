package com.zgcenv.elip.common.service.impl;

import com.zgcenv.elip.common.entity.Users;
import com.zgcenv.elip.common.dao.UsersDao;
import com.zgcenv.elip.common.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-04-02
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

}
