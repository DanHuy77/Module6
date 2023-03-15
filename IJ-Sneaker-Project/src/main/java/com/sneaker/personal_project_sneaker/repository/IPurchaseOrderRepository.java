package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.dto.PaymentHistoryDetailDto;
import com.sneaker.personal_project_sneaker.dto.PaymentHistoryDto;
import com.sneaker.personal_project_sneaker.entity.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

    @Query(value = "select purchase_order.id purchaseOrderId, " +
            "purchase_order.delivery_address deliveryAddress, " +
            "purchase_order.order_date orderDate, " +
            "purchase_order.phone_number phoneNumber, " +
            "purchase_order.total_value totalValue, " +
            "customer.customer_name customerName " +
            "from purchase_order " +
            "join customer " +
            "on purchase_order.customer_id = customer.id " +
            "where customer.id = :customerId " +
            "order by purchase_order.order_date desc"
            , nativeQuery = true,
            countQuery = "select purchase_order.id purchaseOrderId, " +
                    "purchase_order.delivery_address deliveryAddress, " +
                    "purchase_order.order_date orderDate, " +
                    "purchase_order.phone_number phoneNumber, " +
                    "purchase_order.total_value totalValue, " +
                    "customer.customer_name customerName " +
                    "from purchase_order " +
                    "join customer " +
                    "on purchase_order.customer_id = customer.id " +
                    "where customer.id = :customerId " +
                    "order by purchase_order.order_date desc")
    Page<PaymentHistoryDto> getPurchaseOrderByCustomerId(@Param("customerId") Integer customerId, Pageable pageable);


    @Query(value = "select sneaker.color, " +
            "sneaker.price, " +
            "sneaker.producer, " +
            "sneaker.product_code productCode, " +
            "sneaker.sneaker_name sneakerName, " +
            "sneaker_detail.size, " +
            "image.url, " +
            "purchase_order_detail.quantity " +
            "from purchase_order_detail " +
            "join purchase_order " +
            "on purchase_order_detail.purchase_order_id = purchase_order.id " +
            "join sneaker " +
            "on purchase_order_detail.sneaker_sneaker_id = sneaker.sneaker_id " +
            "join sneaker_detail " +
            "on purchase_order_detail.sneaker_detail_id = sneaker_detail.id " +
            "join image " +
            "on image.sneaker_sneaker_id = sneaker.sneaker_id " +
            "where purchase_order.id = :purchaseOrderId " +
            "group by sneaker_detail.size"
            , nativeQuery = true,
            countQuery = "select sneaker.color, " +
                    "sneaker.price, " +
                    "sneaker.producer, " +
                    "sneaker.product_code productCode, " +
                    "sneaker.sneaker_name sneakerName, " +
                    "sneaker_detail.size, " +
                    "image.url, " +
                    "purchase_order_detail.quantity " +
                    "from purchase_order_detail " +
                    "join purchase_order " +
                    "on purchase_order_detail.purchase_order_id = purchase_order.id " +
                    "join sneaker " +
                    "on purchase_order_detail.sneaker_sneaker_id = sneaker.sneaker_id " +
                    "join sneaker_detail " +
                    "on purchase_order_detail.sneaker_detail_id = sneaker_detail.id " +
                    "join image " +
                    "on image.sneaker_sneaker_id = sneaker.sneaker_id " +
                    "where purchase_order.id = :purchaseOrderId " +
                    "group by sneaker_detail.size"
    )
    Page<PaymentHistoryDetailDto> getPurchaseOrderDetail(@Param("purchaseOrderId") Integer purchaseOrderId, Pageable pageable);
}
