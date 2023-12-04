package com.projetsuivi.patientsservice.services;

import com.projetsuivi.patientsservice.dtos.PatientDto;
import com.projetsuivi.patientsservice.enities.AdresseEntity;
import com.projetsuivi.patientsservice.exception.ExceptionHandler;
import com.projetsuivi.patientsservice.helpers.CycleAvoidingMappingContext;
import com.projetsuivi.patientsservice.mappers.AdresseMapper;
import com.projetsuivi.patientsservice.mappers.PatientMapper;
import com.projetsuivi.patientsservice.models.Patient;
import com.projetsuivi.patientsservice.repositories.AdresseRepository;
import com.projetsuivi.patientsservice.repositories.PatientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class PatientService {

    PatientRepository patientRepository;

    AdresseRepository adresseRepository;
    AdresseMapper adresseMapper;
    PatientMapper patientMapper;

    AdresseService adresseService;
    public List<Patient> findAllPatient() {
        try {
            log.info("findAllPatient");
            List<Patient> patientList = new ArrayList<Patient>();
            patientRepository.findAll().forEach(ct -> patientList.add(patientMapper.entityToModel(ct)));
            return  patientList;
        } catch (Exception e) {
            log.error("We could not find all patient: " + e.getMessage());
            throw new ExceptionHandler("We could not find your patients");
        }
    }

    public Patient findPatientById(Long id) {
        try {
            log.info("findPatientById - id: " + id.toString());
            Patient patient = patientMapper.entityToModel(patientRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We didn't find your patient")));
            return patient;
        } catch (Exception e) {
            log.error("We could not find all patient: " + e.getMessage());
            throw new ExceptionHandler("We could not find your patient");
        }
    }

    public Patient createPatient(PatientDto dto) {
        try {
            log.info("createPatient");
            Optional<AdresseEntity> adresse = adresseRepository.findByNumberAndStreet(dto.getAdresse().getNumber(), dto.getAdresse().getStreet());
            if(adresse.isEmpty()){
                 dto.setAdresse(adresseService.createAdresse(adresseMapper.modelToDto(dto.getAdresse())));
            } else{
                dto.setAdresse(adresseMapper.entityToModel(adresse.get()));
            }
            Patient patient = patientMapper.dtoToModel(dto);
            patient.setId(null);
            patientRepository.save(patientMapper.modelToEntity(patient));
            return patient;
        } catch (Exception e) {
            log.error("Couldn't create patient user: " + e.getMessage());
            throw new ExceptionHandler("We could not create your patient");
        }
    }
    public Patient updatePatient(PatientDto dto) {
        try {
            log.info("updatePatient - id: " + dto.getId().toString());
            Patient patient = patientMapper.entityToModel(patientRepository.findById(dto.getId()).orElseThrow(()
                    -> new ExceptionHandler("We could not find your patient")));
            patientMapper.updatePatientFromDto(dto, patient, new CycleAvoidingMappingContext());
            patientRepository.save(patientMapper.modelToEntity(patient));
            return patient;
        } catch (Exception e) {
            log.error("Couldn't update user: " + e.getMessage());
            throw new ExceptionHandler("We could not update your patient");
        }
    }

    public String deletePatient(Long id) {
        try {
            log.info("deletePatient - id: " + id.toString());
            Patient patient = patientMapper.entityToModel(patientRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We could not find your patient")));
            patientRepository.delete(patientMapper.modelToEntity(patient));
            return "Patient deleted";
        } catch (Exception e) {
            log.error("Couldn't delete patient: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your patient");
        }
    }
}
