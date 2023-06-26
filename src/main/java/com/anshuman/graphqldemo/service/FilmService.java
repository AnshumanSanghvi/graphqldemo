package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.FilmMapper;
import com.anshuman.graphqldemo.model.repository.FilmRepository;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public List<FilmRecord> getFilmById(String title) {
        return filmMapper.toDtoList(filmRepository.customGetFilmDetailed(title));
    }
}
