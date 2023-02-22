package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.entity.Image;
import com.sneaker.personal_project_sneaker.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageRestController {

    @Autowired
    private IImageService imageService;

    @GetMapping("")
    public ResponseEntity<List<Image>> getImageList(@RequestParam Integer id, Pageable pageable) {
        List<Image> imageList = imageService.findImagesBySneaker_SneakerId(id, pageable).getContent();
        if (imageList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(imageList, HttpStatus.OK);
    }
}
