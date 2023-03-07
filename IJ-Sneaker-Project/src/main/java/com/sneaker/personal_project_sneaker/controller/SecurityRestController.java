package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.dto.GetIdCustomerView;
import com.sneaker.personal_project_sneaker.dto.request.SignInForm;
import com.sneaker.personal_project_sneaker.dto.response.JwtResponse;
import com.sneaker.personal_project_sneaker.jwt.jwt.JwtProvider;
import com.sneaker.personal_project_sneaker.jwt.jwt.JwtTokenFilter;
import com.sneaker.personal_project_sneaker.jwt.userprincal.AccountPrinciple;
import com.sneaker.personal_project_sneaker.service.IAccountService;
import com.sneaker.personal_project_sneaker.service.ICustomerService;
import com.sneaker.personal_project_sneaker.service.IRoleService;
import com.sneaker.personal_project_sneaker.service.IStaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityRestController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityRestController.class);
    private final
    IAccountService accountService;

    private final
    IRoleService roleService;

    private final
    PasswordEncoder passwordEncoder;

    private final
    AuthenticationManager authenticationManager;

    private final
    JwtProvider jwtProvider;
    private final
    JwtTokenFilter jwtTokenFilter;
    private final ICustomerService customerService;
    private final IStaffService staffService;
//    private final SendMail sendMail;

    public SecurityRestController(
//            SendMail sendMail,
            IAccountService accountService, IRoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider, JwtTokenFilter jwtTokenFilter, ICustomerService customerService, IStaffService staffService) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.jwtTokenFilter = jwtTokenFilter;
        this.customerService = customerService;
        this.staffService = staffService;
//        this.sendMail = sendMail;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login( @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        Integer idCustomer = customerService.findCustomerByEmail(signInForm.getEmail()).getId();
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar(),
                idCustomer,
                accountPrinciple.getAnony()));
    }
}
