package com.mycard.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        final String[] adminAndSystemRoles = {"ADMIN", "SYSTEM"};

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/token").authenticated()
                .antMatchers("/user/{id}").hasAnyRole(adminAndSystemRoles)
                .antMatchers("/user-description/{id}").hasAnyRole(adminAndSystemRoles)
                .antMatchers(HttpMethod.POST).hasAuthority("WRITE_AUTH")
                .antMatchers(HttpMethod.PUT).hasAuthority("UPDATE_AUTH")
                .antMatchers(HttpMethod.GET).hasAuthority("READ_AUTH");
    }
}
