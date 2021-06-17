package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sub_category")
public class SubCategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "category_id")
    private int categoryID;

    @Column(name = "name")
    private String name;

    @Column(name = "enable")
    private boolean enabled;


}
