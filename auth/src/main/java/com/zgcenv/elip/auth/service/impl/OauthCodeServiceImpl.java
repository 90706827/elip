package com.zgcenv.elip.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.auth.dao.OauthCodeDao;
import com.zgcenv.elip.auth.entity.OauthCode;
import com.zgcenv.elip.auth.service.OauthCodeService;
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
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeDao, OauthCode> implements OauthCodeService {

}
