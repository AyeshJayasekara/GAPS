package com.ayeshj.gapstar;

import com.ayeshj.gapstar.model.WeightIndexModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ShippingWeightCalculator {

    default BigDecimal calculateShipping(BigDecimal totalWeight, List<WeightIndexModel> weightIndexModels){

        Optional<WeightIndexModel> optionalWeightIndexModel = weightIndexModels.stream().filter(weightIndexModel ->
                weightIndexModel.isFitToBlock(totalWeight)
        ).findFirst();

        if(optionalWeightIndexModel.isPresent()){
            return optionalWeightIndexModel.get().getAmount();
        }else {
            return BigDecimal.ZERO;
        }
    }
}
