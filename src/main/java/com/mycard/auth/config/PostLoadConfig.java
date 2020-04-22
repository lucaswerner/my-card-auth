package com.mycard.auth.config;

import com.mycard.auth.entity.Privilege;
import com.mycard.auth.entity.Role;
import com.mycard.auth.entity.User;
import com.mycard.auth.repository.PrivilegeRepository;
import com.mycard.auth.repository.RoleRepository;
import com.mycard.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class PostLoadConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void setUp() {

        // card privileges
        final Privilege readCard = getOrSavePrivilege("READ_CARD");
        final Privilege writeCard = getOrSavePrivilege("WRITE_CARD");
        final Privilege updateCard = getOrSavePrivilege("UPDATE_CARD");

        // transaction privileges
        final Privilege readTransaction = getOrSavePrivilege("READ_TRANSACTION");
        final Privilege writeTransaction = getOrSavePrivilege("WRITE_TRANSACTION");
        final Privilege updateTransaction = getOrSavePrivilege("UPDATE_TRANSACTION");

        // auth privileges
        final Privilege readAuth = getOrSavePrivilege("READ_AUTH");

        final List<Privilege> userPrivileges = Arrays.asList(readCard, readTransaction);
        final List<Privilege> systemPrivileges = Arrays.asList(writeCard, writeTransaction, readAuth);
        final List<Privilege> adminPrivileges = Arrays.asList(updateCard, updateTransaction);

        final Role user = getOrSaveRole("USER", userPrivileges);
        final Role system = getOrSaveRole("SYSTEM", systemPrivileges);
        final Role admin = getOrSaveRole("ADMIN", adminPrivileges);

        getOrSaveUser(new User(null, "user", "user@gmail.com", passwordEncoder.encode("user"), true, null, Arrays.asList(user)));
        getOrSaveUser(new User(null, "system", "system@gmail.com", passwordEncoder.encode("system"), true, null, Arrays.asList(system, user)));
        getOrSaveUser(new User(null, "admin", "admin@gmail.com", passwordEncoder.encode("admin"), true, null, Arrays.asList(user, system, admin)));
    }

    private Privilege getOrSavePrivilege(String name) {
        final Optional<Privilege> optionalPrivilege = privilegeRepository.findByName(name);

        if (optionalPrivilege.isPresent()) {
            return optionalPrivilege.get();
        }

        return privilegeRepository.save(new Privilege(name));
    }

    private Role getOrSaveRole(String name, List<Privilege> privilegeList) {
        final Optional<Role> optionalRole = roleRepository.findByName(name);

        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }

        return roleRepository.save(new Role(name, privilegeList));
    }

    private User getOrSaveUser(User user) {
        final Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        return userRepository.save(user);
    }
}
