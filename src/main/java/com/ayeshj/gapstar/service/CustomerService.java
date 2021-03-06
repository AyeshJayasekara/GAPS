package com.ayeshj.gapstar.service;

import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.model.CustomerEntity;
import com.ayeshj.gapstar.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for business logic related to the Customers
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Constructor for dependency injection
     *
     * @param customerRepository Customer Repository {@link CustomerRepository}
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves active list of customers
     *
     * @return List of customer DTOs
     */
    public List<CustomerDTO> fetchAllActiveCustomers() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerRepository.findAll().forEach(customerEntity -> customerDTOList.add(convert(customerEntity)));
        return customerDTOList;
    }

    /**
     * Finds customer by ID
     *
     * @param customerID Customer ID to be searched
     * @return Customer DTO
     */
    public CustomerDTO fetchCustomerByID(int customerID) {

        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerID);
        return optionalCustomerEntity.map(this::convert).orElseGet(CustomerDTO::new);

    }

    /**
     * Converts database entity to DTO
     *
     * @param customerEntity Customer Entity
     * @return Customer DTO
     */
    private CustomerDTO convert(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity, customerDTO);
        return customerDTO;
    }
}
