package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.facade.ICustomerAuthenticatedFacade;
import com.ayeshj.gapstar.model.CustomerEntity;
import com.ayeshj.gapstar.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for functions related to the Customers
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Controller
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final ICustomerAuthenticatedFacade customerAuthenticatedFacade;

    /**
     * Constructor for dependency injection
     *
     * @param customerService             Customer Service {@link CustomerService}
     * @param customerAuthenticatedFacade Customer Authentication Facade
     */
    @Autowired
    public CustomerController(CustomerService customerService,
                              ICustomerAuthenticatedFacade customerAuthenticatedFacade) {
        this.customerService = customerService;
        this.customerAuthenticatedFacade = customerAuthenticatedFacade;
    }

    /**
     * Customer Page
     *
     * @param req HTTP Request to store customer details
     * @return Returns to customer page
     */
    @GetMapping("/customer")
    public String customerPage(HttpServletRequest req) {

        CustomerEntity customerEntity = customerAuthenticatedFacade.fetchAuthenticatedUser();

        if (customerEntity == null) {
            return HomeController.REDIRECT_LOGOUT;
        }

        CustomerDTO customerDTO = customerService.fetchCustomerByID(customerEntity.getId());
        req.getSession().setAttribute("customerID", customerDTO.getId());
        req.getSession().setAttribute("customerFirstName", customerDTO.getFirstName());
        req.getSession().setAttribute("customerLastName", customerDTO.getLastName());

        return "customer";
    }
}
