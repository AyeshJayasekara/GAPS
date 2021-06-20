package com.ayeshj.gapstar.facade;

import com.ayeshj.gapstar.model.CustomerEntity;

/**
 * Facade interface for customer
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
public interface ICustomerAuthenticatedFacade {

    CustomerEntity fetchAuthenticatedUser();
}
