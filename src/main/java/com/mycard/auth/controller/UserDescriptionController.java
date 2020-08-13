package com.mycard.auth.controller;

import com.mycard.auth.dto.UserDescriptionDTO;
import com.mycard.auth.security.MyUserPrincipal;
import com.mycard.auth.service.UserDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user-description")
public class UserDescriptionController {

    private final UserDescriptionService userDescriptionService;

    public UserDescriptionController(UserDescriptionService userDescriptionService) {
        this.userDescriptionService = userDescriptionService;
    }

    @GetMapping
    public ResponseEntity<UserDescriptionDTO> getUserDescription(
            final Authentication authentication
    ) {
        return userDescriptionService.findUserDescriptionDTOById(((MyUserPrincipal) authentication.getPrincipal()).getUser().getId())
                .map(userDescriptionDTO -> ResponseEntity.ok().body(userDescriptionDTO))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping
    public ResponseEntity<UserDescriptionDTO> putUserDescription(
            @RequestBody @Valid UserDescriptionDTO userDescriptionDTO,
            final Authentication authentication
    ) {
        userDescriptionDTO.setId(((MyUserPrincipal) authentication.getPrincipal()).getUser().getId());

        return userDescriptionService.updateUserDescriptionDTO(userDescriptionDTO)
                .map(updatedUserDescription -> ResponseEntity.ok().body(updatedUserDescription))
                .orElseGet(() -> ResponseEntity.status(409).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDescriptionDTO> adminGetUserDescription(
            @PathVariable("id") final Long pathVariableId
    ) {
        return userDescriptionService.findUserDescriptionDTOById(pathVariableId)
                .map(userDescriptionDTO -> ResponseEntity.ok().body(userDescriptionDTO))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
