package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.entity.SlotInCart;
import com.sneaker.personal_project_sneaker.repository.ISlotInCartRepository;
import com.sneaker.personal_project_sneaker.service.ISlotInCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SlotInCartService implements ISlotInCartService {

    @Autowired
    private ISlotInCartRepository slotInCartRepository;

    @Override
    public Page<SlotInCart> findAll(Pageable pageable) {
        return slotInCartRepository.findAll(pageable);
    }

    @Override
    public SlotInCart findById(Integer id) {
        return slotInCartRepository.findById(id).get();
    }

    @Override
    public void save(SlotInCart slotInCart) {
        slotInCartRepository.save(slotInCart);
    }

    @Override
    public void delete(Integer id) {
        slotInCartRepository.deleteById(id);
    }

    @Override
    public SlotInCart findSlotInCartBySneakerDetail_Id(Integer id) {
        return slotInCartRepository.findSlotInCartBySneakerDetail_Id(id);
    }

    @Override
    public Integer countSlotInCartByAccount_IdAccount(Integer idAccount) {
        return slotInCartRepository.countSlotInCartByAccount_IdAccount(idAccount);
    }
}
