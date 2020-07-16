package com.mycard.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PostUserDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate ccInvoiceDt;
    @NotNull
    private String addressStreet;
    @NotNull
    private Integer addressNumber;
    private String addressComplement;
}
