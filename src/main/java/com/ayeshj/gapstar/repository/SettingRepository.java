package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.SettingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for database queries related to the System Settings
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Repository
public interface SettingRepository extends CrudRepository<SettingEntity, String> {
}
