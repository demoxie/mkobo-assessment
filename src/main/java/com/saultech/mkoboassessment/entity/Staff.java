package com.saultech.mkoboassessment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Staff extends UserBaseEntity {
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "registration_date", nullable = true)
    private LocalDate registrationDate;
}
