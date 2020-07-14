package com.mycard.auth.service.impl;

import com.mycard.auth.security.MyUserPrincipal;
import com.mycard.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new MyUserPrincipal(
                userService.findUserDTOByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException(email)));
    }
}
