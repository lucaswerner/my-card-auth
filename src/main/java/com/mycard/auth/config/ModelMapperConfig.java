package com.mycard.auth.config;

import com.mycard.auth.property.map.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        // user
        modelMapper.addMappings(new PostUserDTOToUser());
        modelMapper.addMappings(new UserToCompleteUserDTO());
        modelMapper.addMappings(new UserToUserDTO());
        modelMapper.addMappings(new UserToUserSecurityDTO());

        // user description
        modelMapper.addMappings(new UserDescriptionDTOToUserDescription());
        modelMapper.addMappings(new UserDescriptionToUserDescriptionDTO());

        return modelMapper;
    }
}
