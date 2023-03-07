package com.sneaker.personal_project_sneaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sneaker.personal_project_sneaker.account.Account;

import javax.persistence.*;

@Entity
public class SneakerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer remainQuantity;
    private String size;
    @ManyToOne
    @JsonBackReference
    private Account account;

    public SneakerDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(Integer remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
