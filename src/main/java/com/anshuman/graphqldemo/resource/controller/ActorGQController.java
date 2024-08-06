package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.ActorInfoRecord;
import com.anshuman.graphqldemo.service.ActorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ActorGQController {

    private final ActorService actorService;

    private final HttpServletRequest request;

    @QueryMapping
    public List<ActorInfoRecord> getActorInfoByName(@Argument @PositiveOrZero Integer pageNumber,
            @Argument @PositiveOrZero Integer pageSize, @Argument @NotNull String name) {

        return Optional.ofNullable(request.getHeader("auth"))
                .filter(headerVal -> headerVal.equalsIgnoreCase("1234567"))
                .map(x -> actorService.getActorInfoByName(pageNumber, pageSize, name))
                .orElseThrow(() -> new IllegalAccessError("Missing valid authorization"));
    }
}
