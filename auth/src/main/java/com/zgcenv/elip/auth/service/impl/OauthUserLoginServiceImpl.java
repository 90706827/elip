package com.zgcenv.elip.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.auth.dao.OauthUserLoginDao;
import com.zgcenv.elip.auth.entity.OauthUserLogin;
import com.zgcenv.elip.auth.service.OauthUserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OauthUserLoginServiceImpl extends ServiceImpl<OauthUserLoginDao, OauthUserLogin> implements OauthUserLoginService {

    @Override
    public OauthUserLogin selectByCondition(String providerName, String providerUserId) {
        LambdaQueryWrapper<OauthUserLogin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OauthUserLogin::getProviderName, providerName)
                .eq(OauthUserLogin::getProviderUserId, providerUserId);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<OauthUserLogin> selectByCondition(String username) {
        LambdaQueryWrapper<OauthUserLogin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OauthUserLogin::getUserName, username);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createUserConnection(OauthUserLogin userConnection) {
        this.baseMapper.insert(userConnection);
    }

    @Override
    @Transactional
    public void deleteByCondition(String username, String providerName) {
        LambdaQueryWrapper<OauthUserLogin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OauthUserLogin::getUserName, username);
        queryWrapper.eq(OauthUserLogin::getProviderName, providerName);
        this.remove(queryWrapper);
    }
}
