package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.dto.SneakerDetailDto;
import com.sneaker.personal_project_sneaker.entity.SneakerDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISneakerDetailService extends IGeneralService<SneakerDetail> {
    List<SneakerDetail> findSneakerDetailsByAccount_IdAccount(Integer accountId);

    Page<SneakerDetailDto> getCustomerCart(@Param("accountId") Integer accountId, Pageable pageable);
}
