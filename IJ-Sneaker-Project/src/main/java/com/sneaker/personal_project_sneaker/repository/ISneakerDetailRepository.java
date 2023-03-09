package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.dto.SneakerDetailDto;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import com.sneaker.personal_project_sneaker.entity.SneakerDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ISneakerDetailRepository extends JpaRepository<SneakerDetail, Integer> {


    @Query(value = "select sneaker.sneaker_name sneakerName, " +
            "sneaker.color, " +
            "sneaker.product_code productCode, " +
            "sneaker.price, " +
            "image.url, " +
            "sneaker_detail.id detailId, " +
            "sneaker_detail.size, " +
            "slot_in_cart.in_cart_quantity inCartQuantity " +
            "from sneaker join image " +
            "on sneaker.sneaker_id = image.sneaker_sneaker_id " +
            "join sneaker_sneaker_detail_list " +
            "on sneaker.sneaker_id = sneaker_sneaker_detail_list.sneaker_sneaker_id " +
            "join sneaker_detail " +
            "on sneaker_sneaker_detail_list.sneaker_detail_list_id = sneaker_detail.id " +
            "join slot_in_cart " +
            "on slot_in_cart.sneaker_detail_id = sneaker_detail.id " +
            "where slot_in_cart.account_id_account = :accountId " +
            "group by sneaker_detail.size"
            , nativeQuery = true,
            countQuery = "select sneaker.sneaker_name sneakerName, " +
                    "sneaker.color, " +
                    "sneaker.product_code productCode, " +
                    "sneaker.price, " +
                    "image.url, " +
                    "sneaker_detail.id detailId, " +
                    "sneaker_detail.size, " +
                    "slot_in_cart.in_cart_quantity inCartQuantity " +
                    "from sneaker join image " +
                    "on sneaker.sneaker_id = image.sneaker_sneaker_id " +
                    "join sneaker_sneaker_detail_list " +
                    "on sneaker.sneaker_id = sneaker_sneaker_detail_list.sneaker_sneaker_id " +
                    "join sneaker_detail " +
                    "on sneaker_sneaker_detail_list.sneaker_detail_list_id = sneaker_detail.id " +
                    "join slot_in_cart " +
                    "on slot_in_cart.sneaker_detail_id = sneaker_detail.id " +
                    "where slot_in_cart.account_id_account = :accountId " +
                    "group by sneaker_detail.size")
    Page<SneakerDetailDto> getCustomerCart(@Param("accountId") Integer accountId, Pageable pageable);
}
