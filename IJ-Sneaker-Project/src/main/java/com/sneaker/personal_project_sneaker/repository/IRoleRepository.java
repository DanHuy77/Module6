package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) values (?1,?2)", nativeQuery = true)
    void setDefaultRole(int accountId, Integer roleId);
}
