package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.model.mapper.FilmMapper;
import com.anshuman.graphqldemo.model.repository.FilmRepository;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public List<FilmRecord> getFilmsDetailedByTitle(String title) {
        return filmMapper.toDtoList(filmRepository.customGetFilmDetailed(title));
    }

    public List<FilmRecord> getFilmRecordsByTitle(String title) {
        return filmMapper.toDtoList(new ArrayList<>(filmRepository.getFilmsByTitle(title)));
    }

    public Set<Film> getFilmsByTitle(String title) {
        return filmRepository.getFilmsByTitle(title);
    }
}
