package com.mycard.auth.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostUserDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate ccInvoiceDt;
    private String addressStreet;
    private Integer addressNumber;
    private String addressComplement;
}
