package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.SettingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends CrudRepository<SettingEntity, String> {
}
