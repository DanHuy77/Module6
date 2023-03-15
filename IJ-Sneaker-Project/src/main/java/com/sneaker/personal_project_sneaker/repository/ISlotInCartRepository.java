package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.entity.SlotInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ISlotInCartRepository extends JpaRepository<SlotInCart, Integer> {
    SlotInCart findSlotInCartBySneakerDetail_Id(Integer id);

    Integer countSlotInCartByAccount_IdAccount(@Param("idAccount") Integer idAccount);
}
