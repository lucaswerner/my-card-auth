package com.mycard.auth.config;

import com.mycard.auth.entity.User;
import com.mycard.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Configuration
public class postLoadConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void setUp() {
        final String email = "lucaswerner26@gmail.com";
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {

            final User user = new User();

            user.setEmail(email);
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole("ROLE_ADMIN");

            userRepository.save(user);
        }

        final String userEmail = "user@gmail.com";
        final Optional<User> optionalUser2 = userRepository.findByEmail(userEmail);

        if (optionalUser2.isEmpty()) {

            final User user2 = new User();

            user2.setEmail(userEmail);
            user2.setName("user");
            user2.setPassword(passwordEncoder.encode("user"));
            user2.setRole("ROLE_USER");

            userRepository.save(user2);
        }
    }
}
