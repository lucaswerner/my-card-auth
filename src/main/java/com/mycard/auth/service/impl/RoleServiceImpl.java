package com.mycard.auth.service.impl;

import com.mycard.auth.entity.Role;
import com.mycard.auth.repository.RoleRepository;
import com.mycard.auth.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role getOrSaveRole(Role role) {
        final Optional<Role> optionalRole = getRoleByName(role.getName());

        return optionalRole.orElseGet(() -> roleRepository.save(role));

    }
}
