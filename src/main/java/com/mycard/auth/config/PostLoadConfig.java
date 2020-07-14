package com.mycard.auth.config;

import com.mycard.auth.entity.Privilege;
import com.mycard.auth.entity.Role;
import com.mycard.auth.entity.User;
import com.mycard.auth.entity.UserDescription;
import com.mycard.auth.service.PrivilegeService;
import com.mycard.auth.service.RoleService;
import com.mycard.auth.service.UserService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
public class PostLoadConfig {

    private final UserService userService;
    private final RoleService roleService;
    private final PrivilegeService privilegeService;

    public PostLoadConfig(UserService userService, RoleService roleService, PrivilegeService privilegeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    @PostConstruct
    public void setUp() {

        // card privileges
        final Privilege readCard = privilegeService.getOrSavePrivilege("READ_CARD");
        final Privilege writeCard = privilegeService.getOrSavePrivilege("WRITE_CARD");
        final Privilege updateCard = privilegeService.getOrSavePrivilege("UPDATE_CARD");

        // transaction privileges
        final Privilege readTransaction = privilegeService.getOrSavePrivilege("READ_TRANSACTION");
        final Privilege writeTransaction = privilegeService.getOrSavePrivilege("WRITE_TRANSACTION");
        final Privilege updateTransaction = privilegeService.getOrSavePrivilege("UPDATE_TRANSACTION");

        // auth privileges
        final Privilege readAuth = privilegeService.getOrSavePrivilege("READ_AUTH");
        final Privilege writeAuth = privilegeService.getOrSavePrivilege("WRITE_AUTH");
        final Privilege updateAuth = privilegeService.getOrSavePrivilege("UPDATE_AUTH");

        final List<Privilege> userPrivileges = Arrays.asList(readCard, readTransaction, readAuth, updateAuth);
        final List<Privilege> systemPrivileges = Arrays.asList(writeCard, writeTransaction, writeAuth);
        final List<Privilege> adminPrivileges = Arrays.asList(updateCard, updateTransaction);

        final Role user = roleService.getOrSaveRole(new Role("USER", userPrivileges));
        final Role system = roleService.getOrSaveRole(new Role("SYSTEM", systemPrivileges));
        final Role admin = roleService.getOrSaveRole(new Role("ADMIN", adminPrivileges));

        final UserDescription userDescriptionA = new UserDescription("user firstname", "user lastname", LocalDate.now(), "Rua abacaxi", 123, "Casa");
        final UserDescription userDescriptionB = new UserDescription("user firstname", "user lastname", LocalDate.now(), "Rua abacaxi", 123, "Casa");
        final UserDescription userDescriptionC = new UserDescription("user firstname", "user lastname", LocalDate.now(), "Rua abacaxi", 123, "Casa");

        getOrSaveUser(new User(null, "user@gmail.com", "user", true, null, null, userDescriptionA), Collections.singletonList(user));
        getOrSaveUser(new User(null, "system@gmail.com", "system", true, null, null, userDescriptionB), Arrays.asList(system, user));
        getOrSaveUser(new User(null, "admin@gmail.com", "admin", true, null, null, userDescriptionC), Arrays.asList(user, system, admin));
    }

    private void getOrSaveUser(User user, List<Role> roleList) {
        final Optional<User> optionalUser = userService.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            return;
        }

        userService.saveUser(user, roleList);
    }
}
