package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.view.ActorInfo;
import com.anshuman.graphqldemo.resource.dto.ActorInfoRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActorInfoMapper {

    ActorInfoRecord toDto(ActorInfo actorInfo);

    List<ActorInfoRecord> toDtoList(List<ActorInfo> actorInfo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ActorInfo partialUpdate(ActorInfoRecord actorInfoRecord, @MappingTarget ActorInfo actorInfo);
}