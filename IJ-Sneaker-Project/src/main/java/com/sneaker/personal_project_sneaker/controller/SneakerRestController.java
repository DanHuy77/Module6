package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.account.Account;
import com.sneaker.personal_project_sneaker.dto.*;
import com.sneaker.personal_project_sneaker.entity.*;
import com.sneaker.personal_project_sneaker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @Autowired
    private IPurchaseOrderService purchaseOrderService;
    @Autowired
    private IPurchaseDetailOrderService purchaseDetailOrderService;

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

    @PostMapping("/checkout")
    public ResponseEntity<HttpStatus> checkout(@RequestBody List<CheckoutInfoDto> checkoutInfoDtoList, Pageable pageable) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        Customer customer = checkoutInfoDtoList.get(0).getCustomer();
        purchaseOrder.setCustomer(customer);
        purchaseOrder.setTotalValue(checkoutInfoDtoList.get(0).getTotalValue());
        purchaseOrder.setOrderDate(LocalDate.now().toString());
        purchaseOrder.setPaymentStatus(true);
        purchaseOrder.setPhoneNumber(checkoutInfoDtoList.get(0).getDeliveryPhoneNumber());
        purchaseOrder.setDeliveryAddress(checkoutInfoDtoList.get(0).getDeliveryAddress());
        purchaseOrderService.save(purchaseOrder);
        int size = purchaseOrderService.findAll(pageable).getContent().size();
        PurchaseOrder savedPurchaseOrder = purchaseOrderService.findAll(pageable).getContent().get(size - 1);
        for (int i = 0; i < checkoutInfoDtoList.size(); i++) {
            PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
            purchaseOrderDetail.setPurchaseOrder(savedPurchaseOrder);
            SneakerDetail sneakerDetail = sneakerDetailService.findById(checkoutInfoDtoList.get(i).getDetailId());
            sneakerDetail.setRemainQuantity(sneakerDetail.getRemainQuantity() - checkoutInfoDtoList.get(i).getInCartQuantity());
            if (sneakerDetail.getRemainQuantity() == 0) {
                sneakerDetail.setOutOfStock(true);
            }
            Sneaker sneaker = sneakerService.findById(checkoutInfoDtoList.get(i).getSneakerId());
            purchaseOrderDetail.setSneakerDetail(sneakerDetail);
            purchaseOrderDetail.setSneaker(sneaker);
            purchaseOrderDetail.setQuantity(checkoutInfoDtoList.get(i).getInCartQuantity());
            purchaseDetailOrderService.save(purchaseOrderDetail);
            SlotInCart slotInCart = slotInCartService.findSlotInCartBySneakerDetail_Id(checkoutInfoDtoList.get(i).getDetailId());
            slotInCartService.delete(slotInCart.getSlotId());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/paymentHistory")
    public ResponseEntity<Page<PaymentHistoryDto>> getCustomerPaymentHistory(@RequestParam Integer customerId, Pageable pageable) {
        Page<PaymentHistoryDto> paymentHistoryDtoPage = purchaseOrderService.getPurchaseOrderByCustomerId(customerId, pageable);
        if (paymentHistoryDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentHistoryDtoPage, HttpStatus.OK);
    }

    @GetMapping("/paymentDetail")
    public ResponseEntity<Page<PaymentHistoryDetailDto>> getPaymentDetail(@RequestParam Integer purchaseOrderId, Pageable pageable) {
        Page<PaymentHistoryDetailDto> paymentHistoryDetailDtoPage = purchaseOrderService.getPurchaseOrderDetail(purchaseOrderId, pageable);
        if (paymentHistoryDetailDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentHistoryDetailDtoPage, HttpStatus.OK);
    }

    @GetMapping("/countSlotInCart")
    public ResponseEntity<Integer> getSlotInCartNumber(@RequestParam Integer idAccount) {
        Integer length = slotInCartService.countSlotInCartByAccount_IdAccount(idAccount);
        return new ResponseEntity<>(length, HttpStatus.OK);
    }
}