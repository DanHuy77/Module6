package com.sneaker.personal_project_sneaker.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sneakerId;
    private String sneakerName;
    private String descriptionTitle;
    private String description;
    private String productCode;
    private String producer;
    private Integer price;
    @ManyToOne
    private Category category;
    private boolean deleted;
    private String color;
    private boolean gender;
    @OneToMany
    private List<SneakerDetail> sneakerDetailList;

    public Sneaker() {
    }

    public Integer getSneakerId() {
        return sneakerId;
    }

    public void setSneakerId(Integer sneakerId) {
        this.sneakerId = sneakerId;
    }

    public String getSneakerName() {
        return sneakerName;
    }

    public void setSneakerName(String sneakerName) {
        this.sneakerName = sneakerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public void setDescriptionTitle(String descriptionTitle) {
        this.descriptionTitle = descriptionTitle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public List<SneakerDetail> getSneakerDetailList() {
        return sneakerDetailList;
    }

    public void setSneakerDetailList(List<SneakerDetail> sneakerDetailList) {
        this.sneakerDetailList = sneakerDetailList;
    }
}
