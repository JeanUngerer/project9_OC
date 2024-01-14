package com.projetsuivi.dangerservice.models;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notes {


    private String _id;

    private Long patientId;

    private LocalDateTime dateTime;

    private String note;
}
