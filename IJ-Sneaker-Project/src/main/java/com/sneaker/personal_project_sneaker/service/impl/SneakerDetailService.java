package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.dto.SneakerDetailDto;
import com.sneaker.personal_project_sneaker.entity.SneakerDetail;
import com.sneaker.personal_project_sneaker.repository.ISneakerDetailRepository;
import com.sneaker.personal_project_sneaker.service.ISneakerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SneakerDetailService implements ISneakerDetailService {

    @Autowired
    private ISneakerDetailRepository sneakerDetailRepository;

    @Override
    public Page<SneakerDetail> findAll(Pageable pageable) {
        return sneakerDetailRepository.findAll(pageable);
    }

    @Override
    public SneakerDetail findById(Integer id) {
        return sneakerDetailRepository.findById(id).get();
    }

    @Override
    public void save(SneakerDetail sneakerDetail) {
        sneakerDetailRepository.save(sneakerDetail);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<SneakerDetail> findSneakerDetailsByAccount_IdAccount(Integer accountId) {
        return sneakerDetailRepository.findSneakerDetailsByAccount_IdAccount(accountId);
    }

    @Override
    public Page<SneakerDetailDto> getCustomerCart(Integer accountId, Pageable pageable) {
        return sneakerDetailRepository.getCustomerCart(accountId, pageable);
    }
}
