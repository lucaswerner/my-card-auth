package com.mycard.auth.service;

import com.mycard.auth.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByName(String name);

    Role getOrSaveRole(Role role);
}
