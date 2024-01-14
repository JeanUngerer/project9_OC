package com.projetsuivi.patientsservice.repositories;

import com.projetsuivi.patientsservice.enities.AdresseEntity;
import com.projetsuivi.patientsservice.enities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
