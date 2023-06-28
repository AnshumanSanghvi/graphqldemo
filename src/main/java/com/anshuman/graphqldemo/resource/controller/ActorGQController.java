package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.ActorInfoRecord;
import com.anshuman.graphqldemo.service.ActorService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ActorGQController {

    private final ActorService actorService;

    @QueryMapping
    public List<ActorInfoRecord> getActorInfoByName(@Argument @PositiveOrZero Integer pageNumber,
            @Argument @PositiveOrZero Integer pageSize, @Argument @NotNull String name) {
        return actorService.getActorInfoByName(pageNumber, pageSize, name);
    }
}
