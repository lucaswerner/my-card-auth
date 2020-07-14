package com.mycard.auth.security;

import com.mycard.auth.dto.PrincipalDTO;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public class UserPrincipalExtractor implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        final Map principal = (Map) map.get("principal");
        final Map user = (Map) principal.get("user");

        return new PrincipalDTO(Long.valueOf((Integer) user.get("id")));
    }
}