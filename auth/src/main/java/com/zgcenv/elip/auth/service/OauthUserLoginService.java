package com.zgcenv.elip.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.auth.entity.OauthUserLogin;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
public interface OauthUserLoginService extends IService<OauthUserLogin> {

    OauthUserLogin selectByCondition(String providerName, String providerUserId);

    List<OauthUserLogin> selectByCondition(String username);

    void createUserConnection(OauthUserLogin userConnection);

    void deleteByCondition(String username, String providerName);
}
