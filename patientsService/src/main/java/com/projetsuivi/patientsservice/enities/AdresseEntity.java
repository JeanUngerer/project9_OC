package com.projetsuivi.patientsservice.enities;

import jakarta.persistence.*;
import jakarta.ws.rs.ext.ParamConverter;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AdresseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Number", nullable = false)
    @NonNull
    private Integer number;

    @Column(name = "Street", nullable = false)
    @NonNull
    private String street;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PatientEntity> patients;

}
