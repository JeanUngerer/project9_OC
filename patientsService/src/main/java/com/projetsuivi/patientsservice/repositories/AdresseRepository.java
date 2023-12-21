package com.projetsuivi.patientsservice.repositories;

import com.projetsuivi.patientsservice.enities.AdresseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdresseRepository extends JpaRepository<AdresseEntity, Long> {

    Optional<AdresseEntity> findByNumberAndStreet(Integer number, String street);
}
