package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_level")
    private int stock;

    @Column(name = "unit")
    private String unit;

    @Column(name = "sku")
    private String sku;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "sub_category")
    private int subCategoryID;

    @Column(name = "tax_percentage")
    private BigDecimal taxPercentage;



}
