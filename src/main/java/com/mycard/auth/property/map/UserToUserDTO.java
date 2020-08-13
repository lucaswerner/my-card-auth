package com.mycard.auth.property.map;

import com.mycard.auth.dto.UserDTO;
import com.mycard.auth.entity.User;
import org.modelmapper.PropertyMap;

public class UserToUserDTO extends PropertyMap<User, UserDTO> {
    @Override
    protected void configure() {
        map().setId(source.getId());
        map().setEmail(source.getEmail());
        map().setEnabled(source.getEnabled());
        map().setLastLogin(source.getLastLogin());
    }
}
