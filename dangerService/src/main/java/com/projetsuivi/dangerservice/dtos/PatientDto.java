package com.projetsuivi.dangerservice.dtos;

import com.projetsuivi.dangerservice.constants.Genras;
import com.projetsuivi.dangerservice.models.Adresse;
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
