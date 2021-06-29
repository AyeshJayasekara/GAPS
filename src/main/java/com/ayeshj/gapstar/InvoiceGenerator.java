package com.ayeshj.gapstar;

import com.ayeshj.gapstar.dto.CartDTO;
import com.ayeshj.gapstar.dto.CartItemDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class InvoiceGenerator {


    public void printInvoice(CartDTO cartDTO){

        log.info("ITEM LIST");
        printItemList(cartDTO.getCartItemsList());

        log.info("TOTAL ITEM VALUE: {}", cartDTO.getItemTotalValue());
        log.info("VAT VALUE: {}", cartDTO.getVat());
        log.info("SHIPPING COST: {}", cartDTO.getShippingCost());
        log.info("GRAND TOTAL : {}", cartDTO.getGrandTotal());


    }

    private void printItemList(List<CartItemDTO> cartItemDTOList){

        cartItemDTOList.forEach(cartItemDTO -> {
            log.info("ITEM : {} QUANTITY : {} TOTAL : {}", cartItemDTO.getProduct().getProductName(),
                    cartItemDTO.getQuantity(),
                    cartItemDTO.getTotal());

        });

        log.info("--------------------------------------------------");
    }
}
