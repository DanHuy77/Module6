package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.entity.SneakerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISneakerDetailRepository extends JpaRepository<SneakerDetail, Integer> {
}
