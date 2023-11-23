package com.projetsuivi.patientsservice.dtos;

import com.projetsuivi.patientsservice.models.Patient;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdresseDto {

    private Long id;

    @NonNull
    private Integer number;

    @NonNull
    private String street;

    private List<Patient> patients;
}
