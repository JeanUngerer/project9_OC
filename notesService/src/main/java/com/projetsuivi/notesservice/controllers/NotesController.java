package com.projetsuivi.notesservice.controllers;

import com.projetsuivi.notesservice.dtos.NotesDto;
import com.projetsuivi.notesservice.mappers.NotesMapper;
import com.projetsuivi.notesservice.services.NotesService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("notes")
public class NotesController {

    @Autowired
    NotesService notesService;


    private NotesMapper notesMapper;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Full CRUD for admin
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/notess")
    public ResponseEntity<List<NotesDto>> getNotess() {
        return ResponseEntity.ok(notesMapper.modelsToDtos(notesService.findAllNotes()));
    }

    @GetMapping("/notes/patient/[id}")
    public ResponseEntity<List<NotesDto>> getNotesByPatientId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notesMapper.modelsToDtos(notesService.findAllNotesByPatientId(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotesDto> getNotesById(@PathVariable("id") String id) {
        return ResponseEntity.ok(notesMapper.modelToDto(notesService.findNotesById(id)));
    }

    @PutMapping("/create")
    public ResponseEntity<NotesDto> create(@RequestBody NotesDto notesDto) {
        return ResponseEntity.ok(notesMapper.modelToDto(notesService.createNotes(notesDto)));
    }

    @PostMapping("update")
    public ResponseEntity<NotesDto> update(@RequestBody NotesDto notesDto) {
        return ResponseEntity.ok(notesMapper.modelToDto(notesService.updateNotes(notesDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(notesService.deleteNotes(id));
    }
}
