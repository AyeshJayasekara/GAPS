package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.SubCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for database queries related to the Sub Category
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity, Integer> {

    List<SubCategoryEntity> findAllByEnabledIsTrue();
}
