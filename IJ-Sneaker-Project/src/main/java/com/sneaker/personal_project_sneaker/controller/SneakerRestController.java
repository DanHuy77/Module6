package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.dto.SneakerDto;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import com.sneaker.personal_project_sneaker.service.ISneakerDetailService;
import com.sneaker.personal_project_sneaker.service.ISneakerService;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
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

    @GetMapping("")
    public ResponseEntity<Page<SneakerDto>> getAllSneaker() {
        Pageable pageable = Pageable.ofSize(8);
//        Page<Sneaker> sneakerPage = sneakerService.findAll(pageable);
//        if (sneakerPage.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(sneakerPage, HttpStatus.OK);
        Page<SneakerDto> sneakerDtoPage = sneakerService.getSneakerWithImage(pageable);
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
}