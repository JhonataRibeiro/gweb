package br.com.crud.gweb.model;

/**
 * @author jribeiro
 * @date 30/12/18
 */
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority() {
        return name();
    }

}

