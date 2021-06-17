package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customer/{customerID}")
    public String customerPage(Model model, @PathVariable int customerID, HttpServletRequest req){
        CustomerDTO customerDTO = customerService.fetchCustomerByID(customerID);
        req.getSession().setAttribute("customerID", customerDTO.getId());
        req.getSession().setAttribute("customerFirstName", customerDTO.getFirstName());
        req.getSession().setAttribute("customerLastName", customerDTO.getLastName());
        return "customer";
    }
}
