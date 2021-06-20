package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
     * Constructor for dependency injection
     *
     * @param customerService Customer Service {@link CustomerService}
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Customer Page
     *
     * @param customerID Customer ID
     * @param req        HTTP Request to store customer details
     * @return Returns to customer page
     */
    @GetMapping("/customer/{customerID}")
    public String customerPage(@PathVariable int customerID, HttpServletRequest req) {
        CustomerDTO customerDTO = customerService.fetchCustomerByID(customerID);
        req.getSession().setAttribute("customerID", customerDTO.getId());
        req.getSession().setAttribute("customerFirstName", customerDTO.getFirstName());
        req.getSession().setAttribute("customerLastName", customerDTO.getLastName());
        return "customer";
    }
}
