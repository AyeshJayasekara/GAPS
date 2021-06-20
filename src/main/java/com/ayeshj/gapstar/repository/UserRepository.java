package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for database queries related to the System Users
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
