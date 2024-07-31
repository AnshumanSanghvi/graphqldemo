package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Category;
import com.anshuman.graphqldemo.model.mapper.CategoryMapper;
import com.anshuman.graphqldemo.model.repository.CategoryRepository;
import com.anshuman.graphqldemo.resource.dto.CategoryRecord;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Cacheable(value = "categories", key = "#categoryId")
    public CategoryRecord getCategoryRecordById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Async("APIThreadExecutor")
    public CompletableFuture<Set<Category>> getCategoriesByIds(Set<Short> ids) {
        return categoryRepository.findDistinctCategoryByIdIn(ids);
    }

}
