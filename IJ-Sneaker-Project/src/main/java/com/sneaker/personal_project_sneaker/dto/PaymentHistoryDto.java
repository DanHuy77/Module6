package com.sneaker.personal_project_sneaker.dto;

public interface PaymentHistoryDto {
    Integer getPurchaseOrderId();

    String getDeliveryAddress();

    String getOrderDate();

    String getPhoneNumber();

    Integer getTotalValue();

    String getCustomerName();
}
