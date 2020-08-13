package com.mycard.auth.service;

import com.mycard.auth.entity.Privilege;

import java.util.Optional;

public interface PrivilegeService {
    Optional<Privilege> getPrivilegeByName(String name);

    Privilege getOrSavePrivilege(String name);
}
