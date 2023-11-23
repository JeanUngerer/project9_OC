package com.projetsuivi.notesservice.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesDocument {

    @Id
    private String _id;

    private Long patientId;

    private LocalDateTime dateTime;

    private String note;
}
