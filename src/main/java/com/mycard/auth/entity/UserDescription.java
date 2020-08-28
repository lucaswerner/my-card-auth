package com.mycard.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDescription {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDt = LocalDateTime.now();

    @Column(name = "address_street", nullable = false)
    private String addressStreet;

    @Column(name = "address_number", nullable = false)
    private Integer addressNumber;

    @Column(name = "address_complement")
    private String addressComplement;

    @OneToOne
    @MapsId
    private User user;

    public UserDescription(String firstName, String lastName, String addressStreet, Integer addressNumber, String addressComplement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressComplement = addressComplement;
    }
}
