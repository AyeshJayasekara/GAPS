package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.facade.ICustomerAuthenticatedFacade;
import com.ayeshj.gapstar.model.CustomerEntity;
import com.ayeshj.gapstar.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final ICustomerAuthenticatedFacade customerAuthenticatedFacade;
    public static final String REDIRECT_LOGOUT = "redirect:/logout";

    /**
     * Constructor for dependency injection
     *
     * @param customerService             Customer Service {@link CustomerService}
     * @param customerAuthenticatedFacade Customer Authentication Facade
     */
    @Autowired
    public HomeController(CustomerService customerService,
                          ICustomerAuthenticatedFacade customerAuthenticatedFacade) {
        this.customerService = customerService;
        this.customerAuthenticatedFacade = customerAuthenticatedFacade;
    }


    /**
     * Landing page controller
     *
     * @param model UI model
     * @return Return to home page
     */
    @GetMapping("/")
    public String homePage(Model model, Authentication authentication) {
        CustomerEntity customerEntity = customerAuthenticatedFacade.fetchAuthenticatedUser();

        if (customerEntity != null) {
            return "redirect:/customer";
        }

        User principal = (User) authentication.getPrincipal();

        if (principal.getUsername().equalsIgnoreCase("admin")) {
            model.addAttribute("customers", customerService.fetchAllActiveCustomers());
            return "admin";
        }

        throw new UsernameNotFoundException("UNKNOWN");
    }
}
