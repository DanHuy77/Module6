package com.sneaker.personal_project_sneaker.config;

import com.sneaker.personal_project_sneaker.jwt.jwt.JwtEntryPoint;
import com.sneaker.personal_project_sneaker.jwt.jwt.JwtTokenFilter;
import com.sneaker.personal_project_sneaker.jwt.userprincal.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountDetailService accountDetailService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("api/customer/**", "api/category/**", "api/sneaker/**").access("hasRole('ADMIN')");
//        httpSecurity.authorizeRequests().antMatchers("api/change-avatar", "api/product/**").access("hasAnyRole('ADMIN','USER')");
        httpSecurity.cors().and().csrf().disable()// hu??? CrossOrigin
                .authorizeRequests()
                .antMatchers("/api/public/**", "/sneaker/**", "/image/**") // cho t???t c??? c??c role v??o
                .permitAll()
                .anyRequest()
                .authenticated()// khi c?? account ????ng nh???p
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint) // l???p g??c c???ng
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
