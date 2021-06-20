package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for database queries related to the Products
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
