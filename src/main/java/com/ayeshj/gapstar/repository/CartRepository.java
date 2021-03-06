package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.CartCompositeID;
import com.ayeshj.gapstar.model.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for database queries related to the Cart
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface CartRepository extends CrudRepository<CartEntity, CartCompositeID>{

    List<CartEntity> findAllByCustomerIDAndProductID(int customerID, int productID);
    List<CartEntity> findAllByCustomerID(int customerID);
}
