package com.ayeshj.gapstar.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter
public final class CartDTO {

    private final BigDecimal vat;

    //This mutable field depends on immutable total shipping weight, therefore is immutable automatically.
    @Setter
    private BigDecimal shippingCost;

    private final BigDecimal itemTotalValue;

    private final BigDecimal totalShippingWeight;

    private final List<CartItemDTO> cartItemsList;

    public BigDecimal getGrandTotal() {
        return itemTotalValue.add(shippingCost).add(vat);
    }

    public CartDTO(List<CartItemDTO> cartItemsList) {
        BigDecimal tempVat = BigDecimal.ZERO;
        BigDecimal tempItemTotalValue = BigDecimal.ZERO;
        BigDecimal tempTotalShippingWeight = BigDecimal.ZERO;

        ArrayList<CartItemDTO> cartItemDTOS = new ArrayList<>(cartItemsList); //Copy to maintain immutability

        for (CartItemDTO cartItemDTO : cartItemDTOS){
            tempItemTotalValue = tempItemTotalValue.add(cartItemDTO.getTotal());
            tempVat = tempVat.add(cartItemDTO.getTaxAmount());
            tempTotalShippingWeight = tempTotalShippingWeight.add(cartItemDTO.getWight());
        }

        this.vat = tempVat.setScale(2, RoundingMode.HALF_UP);
        this.itemTotalValue = tempItemTotalValue;
        this.totalShippingWeight = tempTotalShippingWeight;
        this.cartItemsList = cartItemDTOS;
    }
}
