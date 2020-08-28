package com.mycard.auth.property.map;

import com.mycard.auth.dto.PostUserDTO;
import com.mycard.auth.entity.User;
import org.modelmapper.PropertyMap;

public class PostUserDTOToUser extends PropertyMap<PostUserDTO, User> {
    @Override
    protected void configure() {
        map().setEmail(source.getEmail());
        map().setPassword(source.getPassword());
        map().getUserDescription().setFirstName(source.getFirstName());
        map().getUserDescription().setLastName(source.getLastName());
        map().getUserDescription().setAddressStreet(source.getAddressStreet());
        map().getUserDescription().setAddressNumber(source.getAddressNumber());
        map().getUserDescription().setAddressComplement(source.getAddressComplement());
    }
}
