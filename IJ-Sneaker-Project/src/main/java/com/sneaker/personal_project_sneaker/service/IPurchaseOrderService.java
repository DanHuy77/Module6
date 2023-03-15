package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.dto.PaymentHistoryDetailDto;
import com.sneaker.personal_project_sneaker.dto.PaymentHistoryDto;
import com.sneaker.personal_project_sneaker.entity.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IPurchaseOrderService extends IGeneralService<PurchaseOrder>{
    Page<PaymentHistoryDto> getPurchaseOrderByCustomerId(@Param("customerId") Integer customerId, Pageable pageable);

    Page<PaymentHistoryDetailDto> getPurchaseOrderDetail(@Param("purchaseOrderId") Integer purchaseOrderId, Pageable pageable);
}
