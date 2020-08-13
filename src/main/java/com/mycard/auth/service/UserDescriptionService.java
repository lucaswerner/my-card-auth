package com.mycard.auth.service;

import com.mycard.auth.dto.UserDescriptionDTO;
import com.mycard.auth.entity.UserDescription;

import java.util.Optional;

public interface UserDescriptionService {
    Optional<UserDescription> findUserDescriptionById(Long id);

    Optional<UserDescription> updateUserDescription(UserDescription userDescription);

    Optional<UserDescriptionDTO> findUserDescriptionDTOById(Long id);

    Optional<UserDescriptionDTO> updateUserDescriptionDTO(UserDescriptionDTO userDescriptionDTO);
}
