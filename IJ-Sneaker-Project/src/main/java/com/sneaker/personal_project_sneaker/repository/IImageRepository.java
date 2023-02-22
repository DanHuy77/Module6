package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Integer> {
    Page<Image> findImagesBySneaker_SneakerId(Integer id, Pageable pageable);
}
