package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.CartDTO;
import com.ayeshj.gapstar.dto.CartItemDTO;
import com.ayeshj.gapstar.model.CartEntity;
import com.ayeshj.gapstar.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for business logic related to the Cart
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Service
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ShippingCalculatorService shippingCalculatorService;

    /**
     * Constructor for dependency injection
     *
     * @param cartRepository            Cart Repository {@link CartRepository}
     * @param productService            Product Service {@link ProductService}
     * @param shippingCalculatorService Shipping calculator service {@link ShippingCalculatorService}
     */
    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductService productService, ShippingCalculatorService shippingCalculatorService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.shippingCalculatorService = shippingCalculatorService;
    }


    /**
     * Adds a product with specified quantity to user's cart.
     * <p>
     * If no cart is present this will action will initiate a cart for the user
     *
     * @param customerID Customer's ID
     * @param productID  Product ID
     * @param quantity   Quantity to be added to the cart
     */
    public void addToCart(int customerID, int productID, int quantity) {

        Iterable<CartEntity> existingSameCartItems = cartRepository
                .findAllByCustomerIDAndProductID(customerID, productID);

        if (existingSameCartItems.iterator().hasNext()) {
            CartEntity cartEntity = existingSameCartItems.iterator().next();
            cartEntity.setQuantity(cartEntity.getQuantity() + quantity);
            cartRepository.save(cartEntity);
        } else {
            CartEntity cartEntity = new CartEntity();
            cartEntity.setProductID(productID);
            cartEntity.setCustomerID(customerID);
            cartEntity.setQuantity(quantity);
            cartRepository.save(cartEntity);
        }

    }


    /**
     * View existing cart items and shipping costs, VAT for the cart of the user
     *
     * @param customerID Customer ID
     * @return Customer's cart with shipping cost and VAT calculated
     */
    public CartDTO viewCart(int customerID) {

        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        Iterable<CartEntity> cartEntities = cartRepository.findAllByCustomerID(customerID);

        for (CartEntity cartEntity : cartEntities) {

            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProduct(productService.convert(cartEntity.getProduct()));
            cartItemDTO.setQuantity(cartEntity.getQuantity());
            cartItemDTOList.add(cartItemDTO);

        }

        return shippingCalculatorService.calculateShippingForCart(new CartDTO(cartItemDTOList));

    }

    /**
     * Deletes existing items in the user's cart
     *
     * @param customerID Customer ID
     */
    public void deleteCart(int customerID) {
        List<CartEntity> cartEntities = cartRepository.findAllByCustomerID(customerID);
        log.warn("DELETING CART ITEMS FOR USER : {} HAS {} ITEMS", customerID, cartEntities.size());
        cartRepository.deleteAll(cartEntities);
    }
}
