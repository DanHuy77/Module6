package com.sneaker.personal_project_sneaker.service;

import com.sneaker.personal_project_sneaker.account.Account;

import java.util.Optional;

public interface IAccountService extends IGeneralService<Account>{
    Optional<Account> findAccountByUserName(String username);
}
