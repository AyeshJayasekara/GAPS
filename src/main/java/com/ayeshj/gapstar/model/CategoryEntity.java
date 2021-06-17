package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "enable")
    private boolean enabled;


}
