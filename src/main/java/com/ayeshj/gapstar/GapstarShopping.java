package com.ayeshj.gapstar;

import com.ayeshj.gapstar.dto.CartDTO;
import com.ayeshj.gapstar.dto.CartItemDTO;
import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class GapstarShopping implements ShippingWeightCalculator, DemonstrationHelper {

    public void execute(EasyExecutor executor) {

        int demonstrationRounds = getRandomNumber(10);
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

        for (int i = 0; i < demonstrationRounds; i++) {

            addToCart(executor.getCustomers().get(0),
                    getRandomItem(executor.getProducts()),
                    getRandomNumber(20),
                    executor);
        }

        for (int i = 0; i < demonstrationRounds; i++) {

            addToCart(executor.getCustomers().get(1),
                    getRandomItem(executor.getProducts()),
                    getRandomNumber(20),
                    executor);
        }

        log.info("=================== CUSTOMERS HAVE FINISHED SHOPPING =======================");

        executor.getCustomerCart().keySet().forEach(customerDTO -> {

            log.info("################################################################################");

            log.info("CART ITEMS AND SHIPPING INFORMATION FOR CUSTOMER : {}", customerDTO.getFirstName());

            CartDTO cartDTO = executor.getCustomerCart().get(customerDTO);
            invoiceGenerator.printInvoice(cartDTO);

            log.info("################################################################################");


        });


    }


    private void addToCart(CustomerDTO customerDTO, ProductDTO productDTO, int quantity, EasyExecutor executor) {

        log.info("ADDING ITEM : {} TO CART OF CUSTOMER : {}", productDTO.getProductName(), customerDTO.getFirstName());

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProduct(productDTO);
        cartItemDTO.setQuantity(quantity);

        CartDTO cartDTO = executor.getCustomerCart().get(customerDTO);

        if (cartDTO == null) {
            cartDTO = new CartDTO(Collections.singletonList(cartItemDTO));

        } else {
            List<CartItemDTO> cartItemsList = cartDTO.getCartItemsList();
            addToCart(cartItemsList, productDTO, quantity);

            Map<ProductDTO, Integer> newProductsInCart = cartItemsList.stream().collect(Collectors.groupingBy(CartItemDTO::getProduct,
                    Collectors.summingInt(CartItemDTO::getQuantity)));

            cartItemsList = new ArrayList<>();

            Set<ProductDTO> newProducts = newProductsInCart.keySet();

            for (ProductDTO newProduct : newProducts) {
                addToCart(cartItemsList, newProduct, newProductsInCart.get(newProduct));
            }

            cartDTO = new CartDTO(cartItemsList);

        }

        cartDTO.setShippingCost(calculateShipping(cartDTO.getTotalShippingWeight(), executor.getWeightIndexModelList()));

        executor.getCustomerCart().put(customerDTO, cartDTO);

    }

    private void addToCart(List<CartItemDTO> cartItemsList, ProductDTO productDTO, int quantity) {

        CartItemDTO newItem = new CartItemDTO();
        newItem.setQuantity(quantity);
        newItem.setProduct(productDTO);
        cartItemsList.add(newItem);

    }


}
