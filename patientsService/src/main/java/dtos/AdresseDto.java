package dtos;

import lombok.*;
import models.Patient;

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
