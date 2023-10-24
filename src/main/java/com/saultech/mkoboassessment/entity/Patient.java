package com.saultech.mkoboassessment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Patient extends UserBaseEntity{
    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "last_visit_date", nullable = false)
    private LocalDate lastVisitDate;

}
