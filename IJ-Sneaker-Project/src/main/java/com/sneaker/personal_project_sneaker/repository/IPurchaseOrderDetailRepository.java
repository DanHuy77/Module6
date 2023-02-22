package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.entity.PurchaseOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, Integer> {
}
