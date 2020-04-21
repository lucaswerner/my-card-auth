package com.mycard.auth.service.impl;

import com.mycard.auth.entity.User;
import com.mycard.auth.repository.UserRepository;
import com.mycard.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findbyId(Long id) {
        return userRepository.findById(id);
    }
}
