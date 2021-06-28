package com.ayeshj.gapstar.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDTO implements Serializable {

    private int id;

    private String productName;

    private BigDecimal price;

    private int stock;

    private String unit;

    private String sku;

    private BigDecimal weight;

    private int subCategoryID;

    private BigDecimal taxPercentage;

}
