package com.sneaker.personal_project_sneaker.entity;

import com.sneaker.personal_project_sneaker.account.Account;

import javax.persistence.*;

@Entity
public class SlotInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotId;
    @ManyToOne
    private Account account;
    @OneToOne
    private SneakerDetail sneakerDetail;
    private int inCartQuantity;

    public SlotInCart() {
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SneakerDetail getSneakerDetail() {
        return sneakerDetail;
    }

    public void setSneakerDetail(SneakerDetail sneakerDetail) {
        this.sneakerDetail = sneakerDetail;
    }

    public int getInCartQuantity() {
        return inCartQuantity;
    }

    public void setInCartQuantity(int inCartQuantity) {
        this.inCartQuantity = inCartQuantity;
    }
}
