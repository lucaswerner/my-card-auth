package com.mycard.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDescriptionDTO implements Serializable {
    private static final long serialVersionUID = 3853420829509616581L;

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime registerDt;
    private LocalDate ccInvoiceDt;
    private String addressStreet;
    private Integer addressNumber;
    private String addressComplement;
}
