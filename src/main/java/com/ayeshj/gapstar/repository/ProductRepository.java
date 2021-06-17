package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
