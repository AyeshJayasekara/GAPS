package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.SubCategoryDTO;
import com.ayeshj.gapstar.model.SubCategoryEntity;
import com.ayeshj.gapstar.repository.SubCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for business logic related to the Sub Categories
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Service
@Slf4j
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    /**
     * Constructor for dependency injection
     *
     * @param subCategoryRepository Sub category repository {@link SubCategoryRepository}
     */
    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    /**
     * Retrieves a list of active subcategories mapped by it's ID
     *
     * @return Map of subcategories and their IDs
     */
    public Map<Integer, SubCategoryDTO> fetchActiveSubCategories() {
        Map<Integer, SubCategoryDTO> subCategoryMap = new HashMap<>();

        subCategoryRepository.findAllByEnabledIsTrue()
                .forEach(subCategoryEntity -> subCategoryMap
                        .put(subCategoryEntity.getId(), convert(subCategoryEntity)));
        return subCategoryMap;
    }


    /**
     * Converts database entity to DTO
     *
     * @param subCategoryEntity Database entity
     * @return SubCategory DTO
     */
    private SubCategoryDTO convert(SubCategoryEntity subCategoryEntity) {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        BeanUtils.copyProperties(subCategoryEntity, subCategoryDTO);
        return subCategoryDTO;
    }


}
