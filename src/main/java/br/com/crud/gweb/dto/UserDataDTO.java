package br.com.crud.gweb.dto;

import br.com.crud.gweb.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jribeiro
 * @date 30/12/18
 */
@Data
public class UserDataDTO {
    private String username;
    private String email;
    List<Role> roles;
}
