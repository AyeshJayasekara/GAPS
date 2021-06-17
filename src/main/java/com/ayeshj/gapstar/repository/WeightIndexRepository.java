package com.ayeshj.gapstar.repository;

import com.ayeshj.gapstar.model.WeightIndexModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface WeightIndexRepository extends CrudRepository<WeightIndexModel, Integer> {

    @Query(value = "SELECT model FROM WeightIndexModel model WHERE model.blockStart < :amount AND model.blockEnd >= :amount")
    Optional<WeightIndexModel> fetchWeightIndex(BigDecimal amount);
}
