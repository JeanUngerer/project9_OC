package models;

import constants.Genras;

import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
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
