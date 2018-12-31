package br.com.crud.gweb.dto;

import br.com.crud.gweb.model.Role;

import java.util.List;

/**
 * @author jribeiro
 * @date 30/12/18
 */
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    List<Role> roles;
}
