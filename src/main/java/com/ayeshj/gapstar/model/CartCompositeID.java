package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class CartCompositeID implements Serializable {

    @Column(name = "customer_id")
    private int customerID;

    @Column(name = "product_id")
    private int productID;
}
