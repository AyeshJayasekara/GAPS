package com.ayeshj.gapstar.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {

    private BigDecimal vat;
    private BigDecimal shippingCost;
    private BigDecimal itemTotalValue;
    List<CartItemDTO> cartItemsList;

    public BigDecimal getGrandTotal(){
        return itemTotalValue.add(shippingCost).add(vat);
    }
}
