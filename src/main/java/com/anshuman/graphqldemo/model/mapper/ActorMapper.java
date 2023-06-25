package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Actor;
import com.anshuman.graphqldemo.resource.dto.ActorRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {FilmActorMapper.class})
public interface ActorMapper {
    Actor toEntity(ActorRecord actorRecord);

    @AfterMapping
    default void linkFilmActors(@MappingTarget Actor actor) {
        actor.getFilmActors().forEach(filmActor -> filmActor.setActor(actor));
    }

    ActorRecord toDto(Actor actor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actor partialUpdate(ActorRecord actorRecord, @MappingTarget Actor actor);
}