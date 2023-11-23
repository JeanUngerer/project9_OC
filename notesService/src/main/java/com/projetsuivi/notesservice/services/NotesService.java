package com.projetsuivi.notesservice.services;

import com.projetsuivi.notesservice.dtos.NotesDto;
import com.projetsuivi.notesservice.exception.ExceptionHandler;
import com.projetsuivi.notesservice.helpers.CycleAvoidingMappingContext;
import com.projetsuivi.notesservice.mappers.NotesMapper;
import com.projetsuivi.notesservice.models.Notes;
import com.projetsuivi.notesservice.repositories.NotesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
@Transactional
public class NotesService {

    NotesRepository notesRepository;

    NotesMapper notesMapper;
    public List<Notes> findAllNotes() {
        try {
            log.info("findAllNotes");
            List<Notes> notesList = new ArrayList<Notes>();
            notesRepository.findAll().forEach(ct -> notesList.add(notesMapper.documentToModel(ct)));
            return  notesList;
        } catch (Exception e) {
            log.error("We could not find all notes: " + e.getMessage());
            throw new ExceptionHandler("We could not find your notess");
        }
    }

    public List<Notes> findAllNotesByPatientId(Long id) {
        try {
            log.info("findAllNotes");
            List<Notes> notesList = new ArrayList<Notes>();
            notesRepository.findAllNotesByPatientId(id).forEach(ct -> notesList.add(notesMapper.documentToModel(ct)));
            return  notesList;
        } catch (Exception e) {
            log.error("We could not find all notes: " + e.getMessage());
            throw new ExceptionHandler("We could not find your notess");
        }
    }

    public Notes findNotesById(String id) {
        try {
            log.info("findNotesById - id: " + id.toString());
            Notes notes = notesMapper.documentToModel(notesRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We didn't find your notes")));
            return notes;
        } catch (Exception e) {
            log.error("We could not find all notes: " + e.getMessage());
            throw new ExceptionHandler("We could not find your notes");
        }
    }

    public Notes createNotes(NotesDto dto) {
        try {
            log.info("createNotes");
            Notes notes = notesMapper.dtoToModel(dto);
            notes.set_id(null);
            notesRepository.save(notesMapper.modelToDocument(notes));
            return notes;
        } catch (Exception e) {
            log.error("Couldn't notes user: " + e.getMessage());
            throw new ExceptionHandler("We could not create your notes");
        }
    }
    public Notes updateNotes(NotesDto dto) {
        try {
            log.info("updateNotes - id: " + dto.get_id().toString());
            Notes notes = notesMapper.documentToModel(notesRepository.findById(dto.get_id()).orElseThrow(()
                    -> new ExceptionHandler("We could not find your notes")));
            notesMapper.updateNotesFromDto(dto, notes, new CycleAvoidingMappingContext());
            notesRepository.save(notesMapper.modelToDocument(notes));
            return notes;
        } catch (Exception e) {
            log.error("Couldn't update user: " + e.getMessage());
            throw new ExceptionHandler("We could not update your notes");
        }
    }

    public String deleteNotes(String id) {
        try {
            log.info("deleteNotes - id: " + id.toString());
            Notes notes = notesMapper.documentToModel(notesRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We could not find your notes")));
            notesRepository.delete(notesMapper.modelToDocument(notes));
            return "Notes deleted";
        } catch (Exception e) {
            log.error("Couldn't delete notes: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your notes");
        }
    }
}
