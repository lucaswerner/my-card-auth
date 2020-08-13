package com.mycard.auth.service;

import com.mycard.auth.dto.PostUserDTO;
import com.mycard.auth.dto.CompleteUserDTO;
import com.mycard.auth.dto.UserDTO;
import com.mycard.auth.dto.UserSecurityDTO;
import com.mycard.auth.entity.Role;
import com.mycard.auth.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User saveUser(User user);

    User saveUser(User user, List<Role> roleList);

    Optional<UserSecurityDTO> findUserDTOByEmail(String email);

    CompleteUserDTO saveUser(PostUserDTO postUserDTO);

    Optional<UserDTO> findUserDTOById(Long id);
}
