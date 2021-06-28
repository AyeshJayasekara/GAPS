package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test Class For Customer Service
 */
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    void fetchCustomerByIDTest() {
        CustomerDTO firstCustomer = customerService.fetchCustomerByID(2);
        CustomerDTO secondCustomer = customerService.fetchCustomerByID(3);
        assertThat(firstCustomer).isNotNull();
        assertThat(secondCustomer).isNotNull();
        assertThat(firstCustomer.getFirstName()).isEqualTo("Ayesh");
        assertThat(secondCustomer.getFirstName()).isEqualTo("Barrak");
    }
}
