package com.zgcenv.elip.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.auth.entity.OauthClientDetails;
import com.zgcenv.elip.common.exception.ElipAuthException;
import com.zgcenv.elip.common.model.QueryRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-10
 */
public interface OauthClientDetailsService extends IService<OauthClientDetails> {

    IPage<OauthClientDetails> findOAuthClientDetails(QueryRequest request, OauthClientDetails oauthClientDetails);

    OauthClientDetails findById(String clientId);

    void createOAuthClientDetails(OauthClientDetails oauthClientDetails) throws ElipAuthException;

    void updateOAuthClientDetails(OauthClientDetails oauthClientDetails);

    void deleteOAuthClientDetails(String clientIds);
}
