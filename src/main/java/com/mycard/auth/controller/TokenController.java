package com.mycard.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("token")
public class TokenController {

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

}
