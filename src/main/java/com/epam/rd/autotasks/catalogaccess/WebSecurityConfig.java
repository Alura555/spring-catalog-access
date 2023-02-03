package com.epam.rd.autotasks.catalogaccess;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MANAGER = "MANAGER";
    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String CUSTOMER = "CUSTOMER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/salaries/my").hasAnyRole(EMPLOYEE, MANAGER)
                    .antMatchers("/salaries/**").hasAnyRole(MANAGER)
                    .antMatchers(HttpMethod.POST, "/employees/**").hasAnyRole(MANAGER)
                    .antMatchers(HttpMethod.GET, "/employees/**").hasAnyRole(EMPLOYEE, MANAGER)
                .anyRequest().authenticated();
    }

}