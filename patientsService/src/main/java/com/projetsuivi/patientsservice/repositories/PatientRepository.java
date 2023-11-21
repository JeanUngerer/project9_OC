package com.projetsuivi.patientsservice.repositories;

import com.projetsuivi.patientsservice.enities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
