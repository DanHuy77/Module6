package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.dto.SneakerDto;
import com.sneaker.personal_project_sneaker.repository.ISneakerRepository;
import com.sneaker.personal_project_sneaker.service.ISneakerService;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SneakerService implements ISneakerService {

    @Autowired
    private ISneakerRepository sneakerRepository;

    @Override
    public Page<Sneaker> findAll(Pageable pageable) {
        return sneakerRepository.findAll(pageable);
    }

    @Override
    public Sneaker findById(Integer id) {
        return sneakerRepository.findById(id).get();
    }

    @Override
    public void save(Sneaker sneaker) {
        sneakerRepository.save(sneaker);
    }

    @Override
    public void delete(Integer id) {
        sneakerRepository.removeSneaker(id);
    }

    @Override
    public Page<SneakerDto> getSneakerWithImage(Pageable pageable) {
        return sneakerRepository.getSneakerWithImage(pageable);
    }

    @Override
    public Page<Sneaker> getSneakerByKey(String key, Pageable pageable) {
        return sneakerRepository.getSneakerByKey(key, pageable);
    }
}
