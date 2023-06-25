package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.FilmMapper;
import com.anshuman.graphqldemo.model.repository.FilmRepository;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public Optional<FilmRecord> getFilmById(Integer id) {
        return filmRepository.findById(id)
                .map(filmMapper::toDto);
    }
}
