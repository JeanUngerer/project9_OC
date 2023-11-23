package com.projetsuivi.patientsservice.mappers;

import com.projetsuivi.patientsservice.dtos.PatientDto;
import com.projetsuivi.patientsservice.enities.PatientEntity;
import com.projetsuivi.patientsservice.helpers.CycleAvoidingMappingContext;
import com.projetsuivi.patientsservice.models.Patient;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PatientMapper {
    PatientDto modelToDto(Patient Patient);
    List<PatientDto> modelsToDtos(List<Patient> Patient);
    Patient dtoToModel(PatientDto dto);
    List<Patient> dtosToModels(List<PatientDto> dtos);

    PatientEntity modelToEntity(Patient Patient);
    List<PatientEntity> modelsToEntities(List<Patient> Patients);

    Patient entityToModel(PatientEntity entity);


    List<Patient> entitiesToModel(List<PatientEntity> entities);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatientFromModel(Patient model, @MappingTarget PatientEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    void updatePatientFromDto(PatientDto dto, @MappingTarget Patient Patient, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
