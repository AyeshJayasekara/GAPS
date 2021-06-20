package com.ayeshj.gapstar.facade.impl;

import com.ayeshj.gapstar.facade.ICustomerAuthenticatedFacade;
import com.ayeshj.gapstar.model.CustomerEntity;
import com.ayeshj.gapstar.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Facade implementation for Customer
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Component
@Slf4j
public class CustomerAuthenticatedFacade implements ICustomerAuthenticatedFacade {

    private final CustomerRepository customerRepository;

    /**
     * Constructor for dependency injection
     *
     * @param customerRepository Customer Repository
     */
    @Autowired
    public CustomerAuthenticatedFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves the customer entity based on user ID of the logged in user
     *
     * @return Customer entity of the user with logged in user ID
     */
    @Override
    public CustomerEntity fetchAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            User principal = (User) authentication.getPrincipal();
            principal.getUsername();

            log.debug("CHECKING IF USER : {} IS A CUSTOMER", principal.getUsername());

            Optional<CustomerEntity> optionalCustomerEntity = customerRepository
                    .findFirstByUserEntity_Username(principal.getUsername());

            return optionalCustomerEntity.orElse(null);

        }

        return null;
    }
}
