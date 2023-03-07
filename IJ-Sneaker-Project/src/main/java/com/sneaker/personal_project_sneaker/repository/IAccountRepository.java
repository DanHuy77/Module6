package com.sneaker.personal_project_sneaker.repository;

import com.sneaker.personal_project_sneaker.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "update account set deleted = true where id_account = :id", nativeQuery = true)
    void removeAccount(@Param("id") Integer id);

    Optional<Account> findAccountByEmail(String username);
}
