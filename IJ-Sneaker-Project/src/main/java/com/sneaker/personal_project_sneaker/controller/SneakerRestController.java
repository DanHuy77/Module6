package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.account.Account;
import com.sneaker.personal_project_sneaker.dto.SneakerDetailDto;
import com.sneaker.personal_project_sneaker.dto.SneakerDto;
import com.sneaker.personal_project_sneaker.entity.SlotInCart;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import com.sneaker.personal_project_sneaker.entity.SneakerDetail;
import com.sneaker.personal_project_sneaker.service.IAccountService;
import com.sneaker.personal_project_sneaker.service.ISlotInCartService;
import com.sneaker.personal_project_sneaker.service.ISneakerDetailService;
import com.sneaker.personal_project_sneaker.service.ISneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sneaker")
@CrossOrigin("*")
public class SneakerRestController {

    @Autowired
    private ISneakerService sneakerService;
    @Autowired
    private ISneakerDetailService sneakerDetailService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ISlotInCartService slotInCartService;

    @GetMapping("")
    public ResponseEntity<Page<SneakerDto>> getAllSneaker(@RequestParam(defaultValue = "") String key) {
        Pageable pageable = Pageable.ofSize(8);
//        Page<Sneaker> sneakerPage = sneakerService.findAll(pageable);
//        if (sneakerPage.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(sneakerPage, HttpStatus.OK);
        Page<SneakerDto> sneakerDtoPage = sneakerService.getSneakerWithImage(key, pageable);
        if (sneakerDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sneakerDtoPage, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> insertNewSneaker(@RequestBody Sneaker sneaker) {
        sneakerService.save(sneaker);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<HttpStatus> editSneaker(@RequestBody Sneaker sneaker) {
        Sneaker sneaker1 = sneakerService.findById(sneaker.getSneakerId());
        if (sneaker1.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sneakerService.save(sneaker);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> removeSneaker(@RequestParam Integer id) {
        Sneaker sneaker = sneakerService.findById(id);
        if (sneaker == null || sneaker.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sneakerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Sneaker> getSneakerDetail(@RequestParam Integer id) {
        Sneaker sneaker = sneakerService.findById(id);
        if (sneaker == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sneaker, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Sneaker>> getSneakerBySearchKey(@RequestParam(defaultValue = "") String key, Pageable pageable) {
        Page<Sneaker> searchPage = sneakerService.getSneakerByKey(key, pageable);
        if (searchPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(searchPage, HttpStatus.OK);
    }

    @GetMapping("/addToCart")
    public ResponseEntity<HttpStatus> addToCart(@RequestParam Integer sneakerDetailId, @RequestParam Integer accountId) {
        SneakerDetail sneakerDetail = sneakerDetailService.findById(sneakerDetailId);
        Account account = accountService.findById(accountId);
        SlotInCart slotInCart = slotInCartService.findSlotInCartBySneakerDetail_Id(sneakerDetailId);
        if (slotInCart != null) {
            slotInCart.setInCartQuantity(slotInCart.getInCartQuantity() + 1);
            slotInCartService.save(slotInCart);
        } else {
            SlotInCart slotInCart1 = new SlotInCart();
            slotInCart1.setAccount(account);
            slotInCart1.setSneakerDetail(sneakerDetail);
            slotInCart1.setInCartQuantity(1);
            slotInCartService.save(slotInCart1);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/showCart")
    public ResponseEntity<Page<SneakerDetailDto>> showCart(@RequestParam Integer accountId, Pageable pageable) {
        Page<SneakerDetailDto> sneakerDetailDto = sneakerDetailService.getCustomerCart(accountId, pageable);
        if (sneakerDetailDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sneakerDetailDto, HttpStatus.OK);
    }

    @GetMapping("/removeItemFromCart")
    public ResponseEntity<HttpStatus> removeItemFromCart(@RequestParam Integer sneakerDetailId) {
        SlotInCart slotInCart = slotInCartService.findSlotInCartBySneakerDetail_Id(sneakerDetailId);
        if (slotInCart == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        slotInCartService.delete(slotInCart.getSlotId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/changeInCartQuantity")
    public ResponseEntity<HttpStatus> changeItemQuantityInCart(@RequestParam Integer sneakerDetailId, @RequestParam Integer newQuantity) {
        SlotInCart slotInCart = slotInCartService.findSlotInCartBySneakerDetail_Id(sneakerDetailId);
        SneakerDetail sneakerDetail = sneakerDetailService.findById(sneakerDetailId);
        if (newQuantity > sneakerDetail.getRemainQuantity() || newQuantity < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        slotInCart.setInCartQuantity(newQuantity);
        slotInCartService.save(slotInCart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}