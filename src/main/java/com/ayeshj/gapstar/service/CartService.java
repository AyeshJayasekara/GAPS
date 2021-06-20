package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.CartDTO;
import com.ayeshj.gapstar.dto.CartItemDTO;
import com.ayeshj.gapstar.model.CartEntity;
import com.ayeshj.gapstar.model.SettingEntity;
import com.ayeshj.gapstar.model.WeightIndexModel;
import com.ayeshj.gapstar.repository.CartRepository;
import com.ayeshj.gapstar.repository.SettingRepository;
import com.ayeshj.gapstar.repository.WeightIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final SettingRepository settingRepository;
    private final WeightIndexRepository weightIndexRepository;

    /**
     * Constructor for dependency injection
     *
     * @param cartRepository        Cart Repository {@link CartRepository}
     * @param productService        Product Service {@link ProductService}
     * @param settingRepository     Settings Repository {@link SettingRepository}
     * @param weightIndexRepository Shipping cost Repository {@link WeightIndexRepository}
     */
    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       SettingRepository settingRepository, WeightIndexRepository weightIndexRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.settingRepository = settingRepository;
        this.weightIndexRepository = weightIndexRepository;
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

        BigDecimal totalWeight = BigDecimal.ZERO;
        BigDecimal orderTotal = BigDecimal.ZERO;
        CartDTO cartDTO = new CartDTO();
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        Iterable<CartEntity> cartEntities = cartRepository.findAllByCustomerID(customerID);

        for (CartEntity cartEntity : cartEntities) {

            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProduct(productService.convert(cartEntity.getProduct()));
            cartItemDTO.setQuantity(cartEntity.getQuantity());
            cartItemDTOList.add(cartItemDTO);

            orderTotal = orderTotal.add(cartEntity
                    .getProduct().getPrice().multiply(BigDecimal.valueOf(cartEntity.getQuantity())));

            totalWeight = totalWeight.add(cartEntity.getProduct().getWeight()
                    .multiply(BigDecimal.valueOf(cartEntity.getQuantity())));

        }

        Optional<SettingEntity> optionalVATSetting = settingRepository.findById("VAT");

        if (optionalVATSetting.isPresent()) {
            cartDTO.setVat(BigDecimal.valueOf(Double.parseDouble(optionalVATSetting.get().getValue())));
        } else {
            cartDTO.setVat(BigDecimal.ZERO);
        }

        Optional<WeightIndexModel> optionalWeightIndexModel = weightIndexRepository.fetchWeightIndex(totalWeight);

        if (optionalWeightIndexModel.isPresent()) {
            cartDTO.setShippingCost(optionalWeightIndexModel.get().getAmount());
        } else {
            cartDTO.setShippingCost(BigDecimal.ZERO);
        }

        cartDTO.setCartItemsList(cartItemDTOList);
        cartDTO.setItemTotalValue(orderTotal);
        return cartDTO;

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
