package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for functions related to the Home page
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Controller
@Slf4j
public class HomeController {

    private final CustomerService customerService;

    /**
     * Constructor for dependency injection
     *
     * @param customerService Customer Service {@link CustomerService}
     */
    @Autowired
    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }


    /**
     * Landing page controller
     *
     * @param model UI model
     * @return Return to home page
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("customers", customerService.fetchAllActiveCustomers());
        return "home";
    }
}
