package com.projetsuivi.patientsservice.dtos;

import com.projetsuivi.patientsservice.constants.Genras;
import com.projetsuivi.patientsservice.models.Adresse;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDto {

    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private LocalDateTime birthdate;

    @NonNull
    private Genras genra;

    private Adresse adresse;

    private String phone;
}
