package com.sneaker.personal_project_sneaker.service.impl;

import com.sneaker.personal_project_sneaker.entity.Account;
import com.sneaker.personal_project_sneaker.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUserName(username);
        if (account == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        return new User(account.getUserName(), account.getPassword(), new ArrayList<>());
    }
}
