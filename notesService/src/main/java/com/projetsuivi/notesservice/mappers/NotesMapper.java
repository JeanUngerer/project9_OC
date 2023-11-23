package com.projetsuivi.notesservice.mappers;

import com.projetsuivi.notesservice.documents.NotesDocument;
import com.projetsuivi.notesservice.dtos.NotesDto;
import com.projetsuivi.notesservice.helpers.CycleAvoidingMappingContext;
import com.projetsuivi.notesservice.models.Notes;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NotesMapper {

    NotesDto modelToDto(Notes Notes);
    List<NotesDto> modelsToDtos(List<Notes> Notes);
    Notes dtoToModel(NotesDto dto);
    List<Notes> dtosToModels(List<NotesDto> dtos);

    NotesDocument modelToDocument(Notes Notes);
    List<NotesDocument> modelsToEntities(List<Notes> Notess);

    Notes entityToModel(NotesDocument entity);


    List<Notes> entitiesToModel(List<NotesDocument> entities);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNotesFromModel(Notes model, @MappingTarget NotesDocument entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    void updateNotesFromDto(NotesDto dto, @MappingTarget Notes Notes, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
