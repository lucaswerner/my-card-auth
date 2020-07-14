package com.mycard.auth.service.impl;

import com.mycard.auth.dto.CompleteUserDTO;
import com.mycard.auth.dto.PostUserDTO;
import com.mycard.auth.dto.UserDTO;
import com.mycard.auth.dto.UserSecurityDTO;
import com.mycard.auth.entity.Role;
import com.mycard.auth.entity.User;
import com.mycard.auth.enumeration.RoleEnumeration;
import com.mycard.auth.repository.UserRepository;
import com.mycard.auth.service.RoleService;
import com.mycard.auth.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder,
            RoleService roleService
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user, List<Role> roleList) {
        final Optional<User> optionalUserByEmail = userRepository.findByEmail(user.getEmail());

        if (optionalUserByEmail.isPresent()) {
            throw new RuntimeException("User email already registered!");
        }

        user.setRoles(roleList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public Optional<UserSecurityDTO> findUserDTOByEmail(String email) {
        return findByEmail(email)
                .map(user -> modelMapper.map(user, UserSecurityDTO.class));
    }

    public User saveUser(User user) {
        return saveUser(
                user,
                Collections.singletonList(roleService.getRoleByName(RoleEnumeration.USER.toString()).orElseThrow())
        );
    }

    public CompleteUserDTO saveUser(PostUserDTO postUserDTO) {
        return modelMapper.map(
                saveUser(modelMapper.map(postUserDTO, User.class)),
                CompleteUserDTO.class
        );
    }

    @Cacheable(key = "{#id}")
    @HystrixCommand(threadPoolKey = "findUserDTOByIdThreadPool")
    public Optional<UserDTO> findUserDTOById(Long id) {
        return findById(id).map(this::transformUserToUserDTO);
    }

    private UserDTO transformUserToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

}
