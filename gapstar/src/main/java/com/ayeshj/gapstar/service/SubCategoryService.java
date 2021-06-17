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

@Service
@Slf4j
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public Map<Integer, SubCategoryDTO> fetchActiveSubCategories(){
        Map<Integer, SubCategoryDTO> subCategoryMap = new HashMap<>();

        subCategoryRepository.findAllByEnabledIsTrue()
                .forEach(subCategoryEntity -> subCategoryMap
                        .put(subCategoryEntity.getId(), convert(subCategoryEntity)));
        return subCategoryMap;
    }


    private SubCategoryDTO convert(SubCategoryEntity subCategoryEntity){
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        BeanUtils.copyProperties(subCategoryEntity, subCategoryDTO);
        return subCategoryDTO;
    }


}
