package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.entity.PurchaseOrderDetail;
import com.sneaker.personal_project_sneaker.repository.IPurchaseOrderDetailRepository;
import com.sneaker.personal_project_sneaker.service.IPurchaseDetailOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderDetailService implements IPurchaseDetailOrderService {

    @Autowired
    private IPurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Override
    public Page<PurchaseOrderDetail> findAll(Pageable pageable) {
        return purchaseOrderDetailRepository.findAll(pageable);
    }

    @Override
    public PurchaseOrderDetail findById(Integer id) {
        return purchaseOrderDetailRepository.findById(id).get();
    }

    @Override
    public void save(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetailRepository.save(purchaseOrderDetail);
    }

    @Override
    public void delete(Integer id) {

    }
}
