package com.mycard.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2545289432224727076L;
    private Long id;
    private String email;
    private Boolean enabled;
    private LocalDate lastLogin;
}
