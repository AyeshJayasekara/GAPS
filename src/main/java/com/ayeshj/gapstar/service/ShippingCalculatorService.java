package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.CartDTO;
import com.ayeshj.gapstar.model.WeightIndexModel;
import com.ayeshj.gapstar.repository.WeightIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Calculates Shipping cost for the cart
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Service
@Slf4j
public class ShippingCalculatorService {


    private final WeightIndexRepository weightIndexRepository;

    /**
     * Constructor for DI
     *
     * @param weightIndexRepository {@link WeightIndexRepository}
     */
    @Autowired
    public ShippingCalculatorService(WeightIndexRepository weightIndexRepository) {
        this.weightIndexRepository = weightIndexRepository;
    }

    /**
     * Calculates shipping cost for the cart
     *
     * @param cartDTO {@link CartDTO}
     * @return Cart DTO with shipping cost calculated
     */
    public CartDTO calculateShippingForCart(CartDTO cartDTO) {

        Optional<WeightIndexModel> optionalWeightIndexModel = weightIndexRepository
                .fetchWeightIndex(cartDTO.getTotalShippingWeight());

        if(optionalWeightIndexModel.isPresent()){
            cartDTO.setShippingCost(optionalWeightIndexModel.get().getAmount());
        }else {
            cartDTO.setShippingCost(BigDecimal.ZERO);
        }

        return cartDTO;

    }
}
