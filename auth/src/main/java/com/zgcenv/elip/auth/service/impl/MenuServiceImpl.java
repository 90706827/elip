package com.zgcenv.elip.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.auth.dao.MenuDao;
import com.zgcenv.elip.common.entity.Menu;
import com.zgcenv.elip.auth.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

}
