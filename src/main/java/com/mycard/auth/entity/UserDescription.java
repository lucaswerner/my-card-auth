package com.mycard.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDescription {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "register_date")
    private LocalDateTime registerDt;

    @Column(name = "cc_invoice_dt")
    private LocalDate ccInvoiceDt;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_number")
    private Integer addressNumber;

    @Column(name = "address_complement")
    private String addressComplement;

    @OneToOne
    @MapsId
    private User user;

    public UserDescription(String firstName, String lastName, LocalDate ccInvoiceDt, String addressStreet, Integer addressNumber, String addressComplement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ccInvoiceDt = ccInvoiceDt;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressComplement = addressComplement;
    }
}
