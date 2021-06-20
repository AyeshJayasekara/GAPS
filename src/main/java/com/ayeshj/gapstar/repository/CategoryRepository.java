package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for database queries related to the Categories
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    List<CategoryEntity> findAllByEnabledIsTrue();
}
