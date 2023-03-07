package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.account.Account;
import com.sneaker.personal_project_sneaker.repository.IAccountRepository;
import com.sneaker.personal_project_sneaker.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.removeAccount(id);
    }

    @Override
    public Optional<Account> findAccountByUserName(String username) {
        return accountRepository.findAccountByEmail(username);
    }
}
