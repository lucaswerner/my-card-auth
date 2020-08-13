package com.mycard.auth.service.impl;

import com.mycard.auth.entity.Privilege;
import com.mycard.auth.repository.PrivilegeRepository;
import com.mycard.auth.service.PrivilegeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public Optional<Privilege> getPrivilegeByName(String name) {
        return privilegeRepository.findByName(name);
    }

    public Privilege getOrSavePrivilege(String name) {
        final Optional<Privilege> optionalPrivilege = getPrivilegeByName(name);

        return optionalPrivilege.orElseGet(() -> privilegeRepository.save(new Privilege(name)));

    }
}
