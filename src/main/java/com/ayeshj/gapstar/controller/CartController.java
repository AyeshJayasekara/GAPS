package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.facade.ICustomerAuthenticatedFacade;
import com.ayeshj.gapstar.model.CustomerEntity;
import com.ayeshj.gapstar.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for functions related to the Cart
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Controller
@Slf4j
public class CartController {

    private final CartService cartService;
    private final ICustomerAuthenticatedFacade customerAuthenticatedFacade;

    /**
     * Constructor for dependency injection
     *
     * @param cartService                 Cart Service {@link CartService}
     * @param customerAuthenticatedFacade Customer authentication facade
     */
    @Autowired
    public CartController(CartService cartService, ICustomerAuthenticatedFacade customerAuthenticatedFacade) {
        this.cartService = cartService;
        this.customerAuthenticatedFacade = customerAuthenticatedFacade;
    }

    /**
     * Add products to user's cart
     *
     * @param productID ID of the product to be added to the cart
     * @param quantity  Quantity of products to be added to the cart
     * @return Redirect to the product page again
     */
    @PostMapping("/cart/add/{productID}")
    public String addToCart(
            @PathVariable int productID,
            @RequestParam("quantity") int quantity) {


        CustomerEntity customerEntity = customerAuthenticatedFacade.fetchAuthenticatedUser();

        if (customerEntity == null) {
            return "redirect:/logout";
        }

        int customerID = customerEntity.getId();

        cartService.addToCart(customerID, productID, quantity);
        log.info("ADDED PRODUCT {} TO CART OF USER {} FOR {} QUANTITIES", productID, customerID, quantity);

        return "redirect:/products";
    }

    /**
     * CART Template courtesy : https://github.com/sparksuite/simple-html-invoice-template/blob/master/invoice.html
     *
     * @param model UI Model
     * @return Returns to the cart page
     */
    @GetMapping("/cart/view")
    public String cartView(Model model) {

        CustomerEntity customerEntity = customerAuthenticatedFacade.fetchAuthenticatedUser();

        if (customerEntity == null) {
            return "redirect:/logout";
        }

        int customerID = customerEntity.getId();
        model.addAttribute("cart", cartService.viewCart(customerID));
        return "cart";
    }

    /**
     * Existing cart will be deleted for the user
     *
     * @return Redirection to products page
     */
    @PostMapping("/cart/delete")
    public String clearCart() {

        CustomerEntity customerEntity = customerAuthenticatedFacade.fetchAuthenticatedUser();

        if (customerEntity == null) {
            return "redirect:/logout";
        }

        int customerID = customerEntity.getId();

        cartService.deleteCart(customerID);

        return "redirect:/customer";
    }
}
