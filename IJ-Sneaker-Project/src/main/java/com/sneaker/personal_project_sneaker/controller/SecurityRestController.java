package com.sneaker.personal_project_sneaker.controller;

import com.sneaker.personal_project_sneaker.entity.Account;
import com.sneaker.personal_project_sneaker.entity.Customer;
import com.sneaker.personal_project_sneaker.jwt.JwtUtility;
import com.sneaker.personal_project_sneaker.payload.request.LoginRequest;
import com.sneaker.personal_project_sneaker.payload.response.JwtResponse;
import com.sneaker.personal_project_sneaker.service.ICustomerService;
import com.sneaker.personal_project_sneaker.service.impl.AccountService;
import com.sneaker.personal_project_sneaker.service.impl.JwtUserDetailsService;
import com.sneaker.personal_project_sneaker.service.impl.RoleService;
import com.sneaker.personal_project_sneaker.service.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityRestController {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
