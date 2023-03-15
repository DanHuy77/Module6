package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.entity.SlotInCart;
import org.springframework.data.repository.query.Param;

public interface ISlotInCartService extends IGeneralService<SlotInCart>{
    SlotInCart findSlotInCartBySneakerDetail_Id(Integer id);

    Integer countSlotInCartByAccount_IdAccount(@Param("idAccount") Integer idAccount);
}
