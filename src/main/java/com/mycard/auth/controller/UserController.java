package com.mycard.auth.controller;

import com.mycard.auth.dto.UserDTO;
import com.mycard.auth.entity.User;
import com.mycard.auth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        final Optional<User> optionalUser = userService.findbyId(id);

        return optionalUser
                .map(user -> ResponseEntity.ok().body(modelMapper.map(user, UserDTO.class)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
