package com.ayeshj.gapstar.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDTO implements Serializable {

    @EqualsAndHashCode.Include
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
