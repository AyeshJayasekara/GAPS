package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/cart/add/{productID}")
    public String addToCart(Model model,
                               @PathVariable int productID,
                               HttpServletRequest req,
                               @RequestParam("quantity") int quantity){

        int customerID = (int) req.getSession().getAttribute("customerID");

        cartService.addToCart(customerID, productID, quantity);
        log.info("ADDED PRODUCT {} TO CART OF USER {} FOR {} QUANTITIES", productID, customerID, quantity);

        return"redirect:/products";
    }


    /**
     * CART Template courtesy : https://github.com/sparksuite/simple-html-invoice-template/blob/master/invoice.html
     * @param model
     * @param req
     * @return
     */
    @GetMapping("/cart/view")
    public String cartView(Model model, HttpServletRequest req){
        int customerID = (int) req.getSession().getAttribute("customerID");
        model.addAttribute("cart", cartService.viewCart(customerID));
        return"cart";
    }
}
