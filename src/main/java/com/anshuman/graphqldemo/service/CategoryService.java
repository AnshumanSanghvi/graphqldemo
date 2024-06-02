package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.CategoryMapper;
import com.anshuman.graphqldemo.model.repository.CategoryRepository;
import com.anshuman.graphqldemo.resource.dto.CategoryRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryRecord getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

}
