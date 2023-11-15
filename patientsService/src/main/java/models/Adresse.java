package models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adresse {

    private Long id;

    @NonNull
    private Integer number;

    @NonNull
    private String street;

    private List<Patient> patients;
}
