package mappers;

import dtos.AdresseDto;
import enities.AdresseEntity;
import helpers.CycleAvoidingMappingContext;
import models.Adresse;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdresseMapper {

    AdresseDto modelToDto(Adresse myAdresse);
    List<AdresseDto> modelsToDtos(List<Adresse> myAdresse);
    Adresse dtoToModel(AdresseDto dto);
    List<Adresse> dtosToModels(List<AdresseDto> dtos);

    AdresseEntity modelToEntity(Adresse myAdresse);
    List<AdresseEntity> modelsToEntities(List<Adresse> myAdresses);

    Adresse entityToModel(AdresseEntity entity);


    List<Adresse> entitiesToModel(List<AdresseEntity> entities);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdresseFromModel(Adresse model, @MappingTarget AdresseEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    void updateAdresseFromDto(AdresseDto dto, @MappingTarget Adresse myAdresse, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
