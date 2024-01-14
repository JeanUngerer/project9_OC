package com.projetsuivi.dangerservice.proxies;

import com.projetsuivi.dangerservice.dtos.NotesDto;
import com.projetsuivi.dangerservice.dtos.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients-service", url = "app-patient:9031")
public interface PatientProxy {

    @GetMapping(value = "patients/patient/{id}")
    PatientDto getOnePatient(@PathVariable("id") Long id);
}
