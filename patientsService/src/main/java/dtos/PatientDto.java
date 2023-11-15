package dtos;

import constants.Genras;
import lombok.*;
import models.Adresse;

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
