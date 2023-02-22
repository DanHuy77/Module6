package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "update customer set deleted = true where id = :id", nativeQuery = true)
    void removeCustomer(@Param("id") Integer id);
}
