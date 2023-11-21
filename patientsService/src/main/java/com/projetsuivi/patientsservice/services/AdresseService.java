package com.projetsuivi.patientsservice.services;

import com.projetsuivi.patientsservice.dtos.AdresseDto;
import com.projetsuivi.patientsservice.exception.ExceptionHandler;
import com.projetsuivi.patientsservice.helpers.CycleAvoidingMappingContext;
import com.projetsuivi.patientsservice.mappers.AdresseMapper;
import com.projetsuivi.patientsservice.models.Adresse;
import com.projetsuivi.patientsservice.repositories.AdresseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
@Transactional
public class AdresseService {
    AdresseRepository adresseRepository;

    AdresseMapper adresseMapper;
    public List<Adresse> findAllAdresse() {
        try {
            log.info("findAllAdresse");
            List<Adresse> adresseList = new ArrayList<Adresse>();
            adresseRepository.findAll().forEach(ct -> adresseList.add(adresseMapper.entityToModel(ct)));
            return  adresseList;
        } catch (Exception e) {
            log.error("We could not find all adresse: " + e.getMessage());
            throw new ExceptionHandler("We could not find your adresses");
        }
    }

    public Adresse findAdresseById(Long id) {
        try {
            log.info("findAdresseById - id: " + id.toString());
            Adresse adresse = adresseMapper.entityToModel(adresseRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We didn't find your adresse")));
            return adresse;
        } catch (Exception e) {
            log.error("We could not find all adresse: " + e.getMessage());
            throw new ExceptionHandler("We could not find your adresse");
        }
    }

    public Adresse createAdresse(AdresseDto dto) {
        try {
            log.info("createAdresse");
            Adresse adresse = adresseMapper.dtoToModel(dto);
            adresseRepository.save(adresseMapper.modelToEntity(adresse));
            return adresse;
        } catch (Exception e) {
            log.error("Couldn't adresse user: " + e.getMessage());
            throw new ExceptionHandler("We could not create your adresse");
        }
    }
    public Adresse updateAdresse(AdresseDto dto) {
        try {
            log.info("updateAdresse - id: " + dto.getId().toString());
            Adresse adresse = adresseMapper.entityToModel(adresseRepository.findById(dto.getId()).orElseThrow(()
                    -> new ExceptionHandler("We could not find your adresse")));
            adresseMapper.updateAdresseFromDto(dto, adresse, new CycleAvoidingMappingContext());
            adresseRepository.save(adresseMapper.modelToEntity(adresse));
            return adresse;
        } catch (Exception e) {
            log.error("Couldn't update user: " + e.getMessage());
            throw new ExceptionHandler("We could not update your adresse");
        }
    }

    public String deleteAdresse(Long id) {
        try {
            log.info("deleteAdresse - id: " + id.toString());
            Adresse adresse = adresseMapper.entityToModel(adresseRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We could not find your adresse")));
            adresseRepository.delete(adresseMapper.modelToEntity(adresse));
            return "Adresse deleted";
        } catch (Exception e) {
            log.error("Couldn't delete adresse: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your adresse");
        }
    }
}
