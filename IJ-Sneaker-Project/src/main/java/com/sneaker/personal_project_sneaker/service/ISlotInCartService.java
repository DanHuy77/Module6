package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.entity.SlotInCart;

public interface ISlotInCartService extends IGeneralService<SlotInCart>{
    SlotInCart findSlotInCartBySneakerDetail_Id(Integer id);
}
