package com.zgcenv.elip.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.auth.dao.OauthClientDetailsDao;
import com.zgcenv.elip.auth.entity.OauthClientDetails;
import com.zgcenv.elip.common.exception.ElipAuthException;
import com.zgcenv.elip.common.model.QueryRequest;
import com.zgcenv.elip.auth.service.OauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsDao, OauthClientDetails> implements OauthClientDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;

    @Override
    public IPage<OauthClientDetails> findOAuthClientDetails(QueryRequest request, OauthClientDetails oauthClientDetails) {
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(oauthClientDetails.getClientId())) {
            queryWrapper.eq(OauthClientDetails::getClientId, oauthClientDetails.getClientId());
        }
        Page<OauthClientDetails> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<OauthClientDetails> result = this.page(page, queryWrapper);

        List<OauthClientDetails> records = new ArrayList<>();
        result.getRecords().forEach(o -> {
            o.setOriginSecret(null);
            o.setClientSecret(null);
            records.add(o);
        });
        result.setRecords(records);
        return result;
    }

    @Override
    public OauthClientDetails findById(String clientId) {
        return this.baseMapper.selectById(clientId);
    }

    @Override
    @Transactional
    public void createOAuthClientDetails(OauthClientDetails oauthClientDetails) throws ElipAuthException {
        OauthClientDetails byId = this.findById(oauthClientDetails.getClientId());
        if (byId != null) {
            throw new ElipAuthException("该Client已存在");
        }
        oauthClientDetails.setOriginSecret(oauthClientDetails.getClientSecret());
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetails.getClientSecret()));
        boolean saved = this.save(oauthClientDetails);
        if (saved) {
            log.info("缓存Client -> {}", oauthClientDetails);
            this.redisClientDetailsService.loadClientByClientId(oauthClientDetails.getClientId());
        }
    }

    @Override
    @Transactional
    public void updateOAuthClientDetails(OauthClientDetails oauthClientDetails) {
        String clientId = oauthClientDetails.getClientId();

        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OauthClientDetails::getClientId, oauthClientDetails.getClientId());

        oauthClientDetails.setClientId(null);
        oauthClientDetails.setClientSecret(null);
        boolean updated = this.update(oauthClientDetails, queryWrapper);
        if (updated) {
            log.info("更新Client -> {}", oauthClientDetails);
            this.redisClientDetailsService.removeRedisCache(clientId);
            this.redisClientDetailsService.loadClientByClientId(clientId);
        }
    }

    @Override
    @Transactional
    public void deleteOAuthClientDetails(String clientIds) {
        Object[] clientIdArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(clientIds, ",");
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(OauthClientDetails::getClientId, clientIdArray);
        boolean removed = this.remove(queryWrapper);
        if (removed) {
            log.info("删除ClientId为({})的Client", clientIds);
            Arrays.stream(clientIdArray).forEach(c -> this.redisClientDetailsService.removeRedisCache(String.valueOf(c)));

        }
    }
}
