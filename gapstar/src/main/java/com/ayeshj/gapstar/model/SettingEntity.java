package com.ayeshj.gapstar.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "setting")
public class SettingEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;


}
