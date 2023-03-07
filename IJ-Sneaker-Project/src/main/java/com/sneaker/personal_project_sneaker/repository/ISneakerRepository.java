package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.dto.SneakerDto;
import com.sneaker.personal_project_sneaker.entity.Sneaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISneakerRepository extends JpaRepository<Sneaker, Integer> {
    @Query(value = "update sneaker set deleted = true where id = :id", nativeQuery = true)
    void removeSneaker(@Param("id") Integer id);

    @Query(value = "select sneaker.sneaker_id sneakerId" +
            ", sneaker.sneaker_name sneakerName" +
            ", sneaker.price" +
            ", category.category_name categoryName" +
            ", image.url " +
            "from sneaker " +
            "join category " +
            "on sneaker.category_category_id = category.category_id " +
            "join image " +
            "on image.sneaker_sneaker_id = sneaker.sneaker_id " +
            "group by sneaker.sneaker_id " +
            "order by sneakerId desc ", nativeQuery = true,
            countQuery = "select sneaker.sneaker_id sneakerId" +
                    ", sneaker.sneaker_name sneakerName" +
                    ", sneaker.price" +
                    ", category.category_name categoryName" +
                    ", image.url " +
                    "from sneaker " +
                    "join category " +
                    "on sneaker.category_category_id = category.category_id " +
                    "join image " +
                    "on image.sneaker_sneaker_id = sneaker.sneaker_id " +
                    "group by sneaker.sneaker_id " +
                    "order by sneakerId desc ")
    Page<SneakerDto> getSneakerWithImage(Pageable pageable);


}
