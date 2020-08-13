package com.mycard.auth.property.map;

import com.mycard.auth.dto.UserSecurityDTO;
import com.mycard.auth.entity.User;
import org.modelmapper.PropertyMap;

public class UserToUserSecurityDTO extends PropertyMap<User, UserSecurityDTO> {
    @Override
    protected void configure() {
        map().setId(source.getId());
        map().setEmail(source.getEmail());
        map().setEnabled(source.getEnabled());
        map().setLastLogin(source.getLastLogin());
        map().setPassword(source.getPassword());
        map().setRoles(source.getRoles());
    }
}
