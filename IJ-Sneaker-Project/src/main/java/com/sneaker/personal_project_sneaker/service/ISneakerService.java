package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.dto.SneakerDto;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISneakerService extends IGeneralService<Sneaker> {
    Page<SneakerDto> getSneakerWithImage(Pageable pageable);
}
