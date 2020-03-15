package com.zgcenv.elip.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends User {

    private static final long serialVersionUID = -6411066541689297219L;

    private Long id;

    private String loginName;

    private String email;

    private String phone;

    private String displayName;

    private Long headImg;

    private String status;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
