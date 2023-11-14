package com.mapigateway.user.mapper;

import com.mapigateway.helper.CycleAvoidingMappingContext;
import com.mapigateway.user.entity.UserEntity;
import com.mapigateway.user.model.MyUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {



    UserEntity modelToEntity(MyUser myUser);
    List<UserEntity> modelsToEntities(List<MyUser> myUsers);

    MyUser entityToModel(UserEntity entity);


    List<MyUser> entitiesToModel(List<UserEntity> entities);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromModel(MyUser model, @MappingTarget UserEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


}
