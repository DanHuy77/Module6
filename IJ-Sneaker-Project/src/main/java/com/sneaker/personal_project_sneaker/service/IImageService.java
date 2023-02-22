package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IImageService extends IGeneralService<Image> {
    Page<Image> findImagesBySneaker_SneakerId(Integer id, Pageable pageable);
}
