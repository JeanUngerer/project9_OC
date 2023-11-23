package com.projetsuivi.patientsservice.enities;

import com.projetsuivi.patientsservice.constants.Genras;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "firstName", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    @NonNull
    private LocalDateTime birthdate;

    @Column(name = "genra", nullable = false)
    @NonNull
    private Genras genra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id")
    private AdresseEntity adresse;

    @Column(name = "PhoneNumber")
    private String phone;
}
