package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Language;
import com.anshuman.graphqldemo.model.mapper.LanguageMapper;
import com.anshuman.graphqldemo.model.repository.LanguageRepository;
import com.anshuman.graphqldemo.resource.dto.LanguageRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public LanguageRecord getLanguageByFilmId(Integer filmId) {
        return languageMapper.toDto(languageRepository.getLanguageByFilmId(filmId));
    }

    public LanguageRecord getLanguageRecordById(Integer languageId) {
        return languageRepository.findById(languageId)
                .map(languageMapper::toDto)
                .orElse(null);
    }

    public Set<Language> getLanguagesByIds(Set<Integer> languageIds) {
        return languageRepository.getLanguagesByIds(languageIds);
    }
}
