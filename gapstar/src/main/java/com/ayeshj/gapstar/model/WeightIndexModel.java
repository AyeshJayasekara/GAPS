package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "shipping_weight_index")
public class WeightIndexModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private int id;

    @Column(name = "block_start")
    private BigDecimal blockStart;

    @Column(name = "block_end")
    private BigDecimal blockEnd;

    @Column(name = "amount")
    private BigDecimal amount;


}
