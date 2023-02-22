package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.entity.PurchaseOrder;
import com.sneaker.personal_project_sneaker.repository.IPurchaseOrderRepository;
import com.sneaker.personal_project_sneaker.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    private IPurchaseOrderRepository purchaseOrderRepository;

    @Override
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        return purchaseOrderRepository.findAll(pageable);
    }

    @Override
    public PurchaseOrder findById(Integer id) {
        return purchaseOrderRepository.findById(id).get();
    }

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void delete(Integer id) {

    }
}
