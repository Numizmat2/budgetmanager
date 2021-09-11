package com.example.budgetmanager.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="register")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String value;
    private Date timestamp;

    public Register() {
    }

    public Register(String name, String value) {
        this.name = name;
        this.value = value;
        this.timestamp = new Date();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return Double.valueOf(value);
    }

    public Date getTimestamp() {
        return timestamp;
    }


}
