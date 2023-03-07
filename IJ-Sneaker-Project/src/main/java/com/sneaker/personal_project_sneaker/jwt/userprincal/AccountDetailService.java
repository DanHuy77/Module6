package com.sneaker.personal_project_sneaker.jwt.userprincal;


import com.sneaker.personal_project_sneaker.account.Account;
import com.sneaker.personal_project_sneaker.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;
//    @Autowired
//    ICustomerService customerService;

    @Override// tìm account có tồn tại trong DB không
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(() -> new
                UsernameNotFoundException("Account không tìm thấy -> email hoặc passwword" + email));
        return AccountPrinciple.build(account); // gọi lại hàm build bên Accountprinciple để build account mới
    }
}
