package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Language;
import com.anshuman.graphqldemo.model.mapper.LanguageMapper;
import com.anshuman.graphqldemo.model.repository.LanguageRepository;
import com.anshuman.graphqldemo.resource.dto.LanguageRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Cacheable(value = "filmLanguages", key = "#filmId")
    public LanguageRecord getLanguageByFilmId(Integer filmId) {
        return languageMapper.toDto(languageRepository.getLanguageByFilmId(filmId));
    }

    @Cacheable(value = "languages", key = "#languageId")
    public LanguageRecord getLanguageRecordById(Integer languageId) {
        return languageRepository.findById(languageId)
                .map(languageMapper::toDto)
                .orElse(null);
    }

    @Async("APIThreadExecutor")
    public CompletableFuture<Set<Language>> getLanguagesByIds(Set<Integer> languageIds) {
        return languageRepository.getLanguagesByIds(languageIds);
    }
}
