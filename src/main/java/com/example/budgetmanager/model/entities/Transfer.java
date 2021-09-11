package com.example.budgetmanager.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Register originRegister;
    @OneToOne
    private Register targetRegister;
    private Date timestamp;
    private Boolean archived = false;
    private String amount;

    public Transfer() {
    }

    public Transfer(Register originRegister, Register targetRegister, Date timestamp, String amount) {
        this.originRegister = originRegister;
        this.targetRegister = targetRegister;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Register getOriginRegister() {
        return originRegister;
    }

    public Register getTargetRegister() {
        return targetRegister;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Boolean getArchived() {
        return archived;
    }
}
