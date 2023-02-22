package com.sneaker.personal_project_sneaker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGeneralService<T> {
    Page<T> findAll(Pageable pageable);

    T findById(Integer id);

    void save(T t);

    void delete(Integer id);
}
