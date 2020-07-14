package com.mycard.auth.dto;

import com.mycard.auth.entity.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class UserSecurityDTO extends UserDTO {
    private String password;
    private Collection<Role> roles;
}
