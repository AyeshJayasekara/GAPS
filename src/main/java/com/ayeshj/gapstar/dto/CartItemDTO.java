package com.ayeshj.gapstar.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class CartItemDTO implements Serializable {


    private ProductDTO product;

    private int quantity;

    public BigDecimal getTotal(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTaxAmount(){
        return getTotal().multiply(getProduct().getTaxPercentage())
                .divide(BigDecimal.valueOf(100.00), RoundingMode.CEILING);
    }

    public BigDecimal getWight(){
        return getProduct().getWeight().multiply(BigDecimal.valueOf(quantity));
    }


}
