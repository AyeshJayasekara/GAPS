package com.ayeshj.gapstar.dto;

import lombok.Data;

import java.io.Serializable;

@Data

public class SubCategoryDTO implements Serializable {

    private int id;

    private int categoryID;

    private String name;

    private boolean enabled;


}
