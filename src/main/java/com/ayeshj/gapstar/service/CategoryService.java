package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.CategoryDTO;
import com.ayeshj.gapstar.model.CategoryEntity;
import com.ayeshj.gapstar.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for business logic related to the Categories
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Constructor for dependency injection
     *
     * @param categoryRepository Category Repository {@link CategoryRepository}
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    /**
     * Retrieves currently active categories
     *
     * @return Map of category ID's and Category DTOs
     */
    public Map<Integer, CategoryDTO> fetchActiveCategories() {
        Map<Integer, CategoryDTO> categoryMap = new HashMap<>();
        categoryRepository.findAllByEnabledIsTrue()
                .forEach(categoryEntity -> categoryMap.put(categoryEntity.getId(), convert(categoryEntity)));

        return categoryMap;
    }


    /**
     * Convert the Entity to DTO
     *
     * @param categoryEntity Database Entity
     * @return Converted DTO
     */
    private CategoryDTO convert(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(categoryEntity, categoryDTO);
        return categoryDTO;
    }


}
