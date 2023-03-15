package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.entity.Customer;
import com.sneaker.personal_project_sneaker.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customerInfo")
    public ResponseEntity<Customer> getCustomerInfo(@RequestParam Integer id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
