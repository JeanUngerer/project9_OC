package com.projetsuivi.dangerservice.models;


import com.projetsuivi.dangerservice.constants.Genras;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Patient {

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
