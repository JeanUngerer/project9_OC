package com.projetsuivi.patientsservice.mappers;

import com.projetsuivi.patientsservice.dtos.AdresseDto;
import com.projetsuivi.patientsservice.enities.AdresseEntity;
import com.projetsuivi.patientsservice.helpers.CycleAvoidingMappingContext;
import com.projetsuivi.patientsservice.models.Adresse;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdresseMapper {

    AdresseDto modelToDto(Adresse Adresse);
    List<AdresseDto> modelsToDtos(List<Adresse> Adresse);
    Adresse dtoToModel(AdresseDto dto);
    List<Adresse> dtosToModels(List<AdresseDto> dtos);

    AdresseEntity modelToEntity(Adresse Adresse);
    List<AdresseEntity> modelsToEntities(List<Adresse> Adresses);

    Adresse entityToModel(AdresseEntity entity);


    List<Adresse> entitiesToModel(List<AdresseEntity> entities);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdresseFromModel(Adresse model, @MappingTarget AdresseEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    void updateAdresseFromDto(AdresseDto dto, @MappingTarget Adresse Adresse, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
