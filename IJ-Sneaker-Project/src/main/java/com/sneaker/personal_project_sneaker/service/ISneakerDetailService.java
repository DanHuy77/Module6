package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.entity.SneakerDetail;

import java.util.List;

public interface ISneakerDetailService extends IGeneralService<SneakerDetail> {
    List<SneakerDetail> findSneakerDetailsByAccount_IdAccount(Integer accountId);
}
