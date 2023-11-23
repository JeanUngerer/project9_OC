package com.projetsuivi.notesservice.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notes {


    private String _id;

    private Long patientId;

    private LocalDateTime dateTime;

    private String note;
}
