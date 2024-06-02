package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.FilmMapper;
import com.anshuman.graphqldemo.model.repository.FilmRepository;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public List<FilmRecord> getFilmsDetailedByTitle(String title) {
        return filmMapper.toDtoList(filmRepository.customGetFilmDetailed(title));
    }

    public List<FilmRecord> getFilmsByTitle(String title) {
        return filmMapper.toDtoList(filmRepository.getFilmByTitle(title));
    }
}
