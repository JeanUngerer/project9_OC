package com.projetsuivi.patientsservice.controllers;

import com.projetsuivi.patientsservice.dtos.AdresseDto;
import com.projetsuivi.patientsservice.mappers.AdresseMapper;
import com.projetsuivi.patientsservice.services.AdresseService;
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
@RequestMapping("patients/adresse")
public class AdresseController {
    @Autowired
    AdresseService adresseService;


    private AdresseMapper adresseMapper;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Full CRUD for admin
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/adresses")
    public ResponseEntity<List<AdresseDto>> getAdresses() {
        return ResponseEntity.ok(adresseMapper.modelsToDtos(adresseService.findAllAdresse()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseDto> getAdresseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(adresseMapper.modelToDto(adresseService.findAdresseById(id)));
    }

    @PutMapping("/create")
    public ResponseEntity<AdresseDto> create(@RequestBody AdresseDto adresseDto) {
        return ResponseEntity.ok(adresseMapper.modelToDto(adresseService.createAdresse(adresseDto)));
    }

    @PostMapping("update")
    public ResponseEntity<AdresseDto> update(@RequestBody AdresseDto adresseDto) {
        return ResponseEntity.ok(adresseMapper.modelToDto(adresseService.updateAdresse(adresseDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(adresseService.deleteAdresse(id));
    }
}
