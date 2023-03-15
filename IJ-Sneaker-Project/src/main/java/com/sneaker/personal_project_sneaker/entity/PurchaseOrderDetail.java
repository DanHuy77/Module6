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
    @OneToOne
    private SneakerDetail sneakerDetail;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public SneakerDetail getSneakerDetail() {
        return sneakerDetail;
    }

    public void setSneakerDetail(SneakerDetail sneakerDetail) {
        this.sneakerDetail = sneakerDetail;
    }
}

