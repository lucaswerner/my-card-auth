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
import java.util.Optional;

@Configuration
public class postLoadConfig {

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
        final String email = "lucaswerner26@gmail.com";

        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return;
        }

        final Privilege read = privilegeRepository.save(new Privilege("READ"));
        final Privilege update = privilegeRepository.save(new Privilege("UPDATE"));
        final Privilege delete = privilegeRepository.save(new Privilege("DELETE"));
        final Privilege write = privilegeRepository.save(new Privilege("WRITE"));

        final Role admin = roleRepository.save(new Role("ADMIN", Arrays.asList(read, update, delete, write)));

        final User user = new User();

        user.setEmail(email);
        user.setName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles(Arrays.asList(admin));

        userRepository.save(user);
    }
}
