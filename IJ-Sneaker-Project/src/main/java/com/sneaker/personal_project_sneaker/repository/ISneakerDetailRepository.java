package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.dto.SneakerDetailDto;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import com.sneaker.personal_project_sneaker.entity.SneakerDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISneakerDetailRepository extends JpaRepository<SneakerDetail, Integer> {
    List<SneakerDetail> findSneakerDetailsByAccount_IdAccount(Integer accountId);


    @Query(value = "select sneaker.sneaker_name sneakerName, " +
            "sneaker.color, " +
            "sneaker.product_code productCode, " +
            "sneaker.price, " +
            "image.url, " +
            "sneaker_detail.id detailId, " +
            "sneaker_detail.size " +
            "from sneaker join image " +
            "on sneaker.sneaker_id = image.sneaker_sneaker_id " +
            "join sneaker_sneaker_detail_list " +
            "on sneaker.sneaker_id = sneaker_sneaker_detail_list.sneaker_sneaker_id " +
            "join sneaker_detail " +
            "on sneaker_sneaker_detail_list.sneaker_detail_list_id = sneaker_detail.id " +
            "join account " +
            "on account.id_account = sneaker_detail.account_id_account " +
            "where account.id_account = :accountId " +
            "group by sneaker.sneaker_id"
            , nativeQuery = true,
    countQuery = "select sneaker.sneaker_name sneakerName, " +
            "sneaker.color, " +
            "sneaker.product_code productCode, " +
            "sneaker.price, " +
            "image.url, " +
            "sneaker_detail.id detailId, " +
            "sneaker_detail.size " +
            "from sneaker join image " +
            "on sneaker.sneaker_id = image.sneaker_sneaker_id " +
            "join sneaker_sneaker_detail_list " +
            "on sneaker.sneaker_id = sneaker_sneaker_detail_list.sneaker_sneaker_id " +
            "join sneaker_detail " +
            "on sneaker_sneaker_detail_list.sneaker_detail_list_id = sneaker_detail.id " +
            "join account " +
            "on account.id_account = sneaker_detail.account_id_account " +
            "where account.id_account = :accountId " +
            "group by sneaker.sneaker_id")
    Page<SneakerDetailDto> getCustomerCart(@Param("accountId") Integer accountId, Pageable pageable);
}
