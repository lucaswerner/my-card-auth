package com.mycard.auth.controller;

import com.mycard.auth.dto.CompleteUserDTO;
import com.mycard.auth.dto.PostUserDTO;
import com.mycard.auth.dto.UserDTO;
import com.mycard.auth.security.MyUserPrincipal;
import com.mycard.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserData(
            final Authentication authentication
    ) {
        return userService.findUserDTOById(((MyUserPrincipal) authentication.getPrincipal()).getUser().getId())
                .map(userDTO -> ResponseEntity.ok().body(userDTO))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<CompleteUserDTO> postUser(@Valid @RequestBody PostUserDTO postUserDTO) {
        return ResponseEntity.ok().body(userService.saveUser(postUserDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> systemGetUserData(
            @PathVariable("id") final Long pathVariableId
    ) {
        return userService.findUserDTOById(pathVariableId)
                .map(userDTO -> ResponseEntity.ok().body(userDTO))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
