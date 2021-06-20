package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customer_cart")
@IdClass(CartCompositeID.class)
public class CartEntity implements Serializable {

    @Id
    @Column(name = "customer_id")
    private int customerID;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;

    @Column(name = "product_id")
    private int productID;

    @Column(name = "quantity")
    private int quantity;

}
