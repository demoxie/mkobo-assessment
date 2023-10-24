package com.saultech.mkoboassessment.repository;

import com.saultech.mkoboassessment.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByAgeGreaterThanEqual(Integer minimumAge);

    Page<Patient> findAllByAgeBetween(Integer minimumAge, Integer maximumAge, Pageable pageable);

    Optional<Patient> findByName(String name);

    void deleteAllByAgeBetween(Integer minimumAge, Integer maximumAge);
}
