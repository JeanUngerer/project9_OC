package enities;

import constants.Genras;
import jakarta.persistence.*;
import lombok.*;
import models.Adresse;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FirstName", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "FirstName", nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "FirstName", nullable = false)
    @NonNull
    private LocalDateTime birthdate;

    @Column(name = "FirstName", nullable = false)
    @NonNull
    private Genras genra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private AdresseEntity adresse;

    @Column(name = "PhoneNumber")
    private String phone;
}
