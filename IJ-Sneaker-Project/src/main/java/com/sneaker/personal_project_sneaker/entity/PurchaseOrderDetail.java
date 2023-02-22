package com.sneaker.personal_project_sneaker.entity;

import javax.persistence.*;

@Entity
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Sneaker sneaker;
    @ManyToOne
    private PurchaseOrder purchaseOrder;
    private Integer quantity;

    public PurchaseOrderDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer buyQuantityId) {
        this.id = buyQuantityId;
    }

    public Sneaker getSneaker() {
        return sneaker;
    }

    public void setSneaker(Sneaker sneaker) {
        this.sneaker = sneaker;
    }

    public PurchaseOrder getOrder() {
        return purchaseOrder;
    }

    public void setOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
