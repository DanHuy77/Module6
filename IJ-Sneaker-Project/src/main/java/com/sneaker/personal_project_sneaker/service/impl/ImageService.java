package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.entity.Image;
import com.sneaker.personal_project_sneaker.repository.IImageRepository;
import com.sneaker.personal_project_sneaker.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public Image findById(Integer id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Page<Image> findImagesBySneaker_SneakerId(Integer id, Pageable pageable) {
        return imageRepository.findImagesBySneaker_SneakerId(id, pageable);
    }

//    @Override
//    public Page<Image> findImagesBySneaker_Id(Integer id, Pageable pageable) {
//        return imageRepository.findImagesBySneaker_Id(id, pageable);
//    }
}
