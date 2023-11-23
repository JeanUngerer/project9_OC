package com.projetsuivi.dangerservice.mappers;


import com.projetsuivi.dangerservice.dtos.NotesDto;
import com.projetsuivi.dangerservice.dtos.PatientDto;
import com.projetsuivi.dangerservice.models.Notes;
import com.projetsuivi.dangerservice.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DangerWordsMapper {
    
    PatientDto patientToDto(Patient model);
    
    Patient dtoToPatient(PatientDto dto);

    List<PatientDto> patientsToDtos(List<Patient> model);

    List<Patient> dtosToPatients(List<PatientDto> dto);

    NotesDto notesToDto(Notes model);

    Notes dtoToNotes(NotesDto dto);

    List<NotesDto> notesToDtos(List<Notes> model);

    List<Notes> dtosToNotes(List<NotesDto> dto);
}
