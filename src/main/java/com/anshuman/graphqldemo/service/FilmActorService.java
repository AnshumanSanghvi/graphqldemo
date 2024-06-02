package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.FilmActorMapper;
import com.anshuman.graphqldemo.model.repository.FilmActorRepository;
import com.anshuman.graphqldemo.resource.dto.FilmActorRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class FilmActorService {
    private final FilmActorRepository filmActorRepository;
    private final FilmActorMapper filmActorMapper;

    public Set<FilmActorRecord> getFilmActorByFilmId(Integer filmId) {
        return filmActorMapper.toDtoSet(filmActorRepository.getActorsByFilmId(filmId));
    }
}
