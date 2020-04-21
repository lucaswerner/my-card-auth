package com.mycard.auth.service;

import com.mycard.auth.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findbyId(Long id);
}
