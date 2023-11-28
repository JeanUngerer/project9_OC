package com.projetsuivi.patientsservice.controllers;

import com.projetsuivi.patientsservice.dtos.PatientDto;
import com.projetsuivi.patientsservice.mappers.PatientMapper;
import com.projetsuivi.patientsservice.services.PatientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("patients/patient")
public class PatientController {

    @Autowired
    PatientService patientService;


    private PatientMapper patientMapper;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Full CRUD for admin
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDto>> getPatients() {
        return ResponseEntity.ok(patientMapper.modelsToDtos(patientService.findAllPatient()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientMapper.modelToDto(patientService.findPatientById(id)));
    }

    @PutMapping("/create")
    public ResponseEntity<PatientDto> create(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientMapper.modelToDto(patientService.createPatient(patientDto)));
    }

    @PostMapping("update")
    public ResponseEntity<PatientDto> update(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientMapper.modelToDto(patientService.updatePatient(patientDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientService.deletePatient(id));
    }
}
