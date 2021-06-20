package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for database queries related to the Customers
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

}
