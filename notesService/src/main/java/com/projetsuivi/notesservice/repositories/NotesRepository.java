package com.projetsuivi.notesservice.repositories;

import com.projetsuivi.notesservice.documents.NotesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<NotesDocument, String> {

}
