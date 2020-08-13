package com.mycard.auth.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompleteUserDTO {
    private Long id;
    private String email;
    private Boolean enabled;
    private LocalDate lastLogin;
    private String firstName;
    private String lastName;
    private LocalDate ccInvoiceDt;
    private String addressStreet;
    private Integer addressNumber;
    private String addressComplement;
}
