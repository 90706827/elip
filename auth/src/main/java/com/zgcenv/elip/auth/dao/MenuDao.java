package com.zgcenv.elip.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgcenv.elip.common.entity.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> findUserPermissions(String username);
}
