package com.zgcenv.elip.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.auth.dao.OauthAccessTokenDao;
import com.zgcenv.elip.auth.entity.OauthAccessToken;
import com.zgcenv.elip.auth.service.OauthAccessTokenService;
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
public class OauthAccessTokenServiceImpl extends ServiceImpl<OauthAccessTokenDao, OauthAccessToken> implements OauthAccessTokenService {

}
