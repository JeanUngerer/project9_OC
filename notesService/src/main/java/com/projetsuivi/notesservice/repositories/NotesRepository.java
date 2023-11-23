package com.projetsuivi.notesservice.repositories;

import com.projetsuivi.notesservice.documents.NotesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository<NotesDocument, String> {
    List<NotesDocument> findAllNotesByPatientId(Long id);
}
