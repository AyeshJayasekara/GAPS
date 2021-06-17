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

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Map<Integer, CategoryDTO> fetchActiveCategories(){
        Map<Integer, CategoryDTO> categoryMap = new HashMap<>();
        categoryRepository.findAllByEnabledIsTrue()
                .forEach(categoryEntity -> categoryMap.put(categoryEntity.getId(), convert(categoryEntity)));

        return categoryMap;
    }


    private CategoryDTO convert(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(categoryEntity, categoryDTO);
        return categoryDTO;
    }


}
