package com.zgcenv.elip.auth.controller;

/**
 * @ClassName OAuthClientDetailsController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/8 21:35
 * @Version 1.0
 **/

import com.zgcenv.elip.auth.entity.OauthClientDetails;
import com.zgcenv.elip.common.exception.ElipAuthException;
import com.zgcenv.elip.common.model.ElipResponse;
import com.zgcenv.elip.common.model.QueryRequest;
import com.zgcenv.elip.auth.service.OauthClientDetailsService;
import com.zgcenv.elip.common.utils.ElipUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("client")
public class OAuthClientDetailsController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @GetMapping("check/{clientId}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String clientId) {
        OauthClientDetails client = oauthClientDetailsService.findById(clientId);
        return client == null;
    }

    @GetMapping("secret/{clientId}")
    @PreAuthorize("hasAuthority('client:decrypt')")
    public ElipResponse getOriginClientSecret(@NotBlank(message = "{required}") @PathVariable String clientId) {
        OauthClientDetails client = oauthClientDetailsService.findById(clientId);
        String origin = client != null ? client.getOriginSecret() : StringUtils.EMPTY;
        return new ElipResponse().data(origin);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('client:view')")
    public ElipResponse oauthCliendetailsList(QueryRequest request, OauthClientDetails oAuthClientDetails) {
        Map<String, Object> dataTable = ElipUtil.getDataTable(oauthClientDetailsService.findOAuthClientDetails(request, oAuthClientDetails));
        return new ElipResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('client:add')")
    public void addOauthCliendetails(@Valid OauthClientDetails oAuthClientDetails) throws ElipAuthException {
        try {
            oauthClientDetailsService.createOAuthClientDetails(oAuthClientDetails);
        } catch (Exception e) {
            String message = "新增客户端失败";
            log.error(message, e);
            throw new ElipAuthException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('client:delete')")
    public void deleteOauthCliendetails(@NotBlank(message = "{required}") String clientIds) throws ElipAuthException {
        try {
            oauthClientDetailsService.deleteOAuthClientDetails(clientIds);
        } catch (Exception e) {
            String message = "删除客户端失败";
            log.error(message, e);
            throw new ElipAuthException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('client:update')")
    public void updateOauthCliendetails(@Valid OauthClientDetails oAuthClientDetails) throws ElipAuthException {
        try {
            oauthClientDetailsService.updateOAuthClientDetails(oAuthClientDetails);
        } catch (Exception e) {
            String message = "修改客户端失败";
            log.error(message, e);
            throw new ElipAuthException(message);
        }
    }
}
