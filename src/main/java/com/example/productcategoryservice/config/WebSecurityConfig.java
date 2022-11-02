package com.example.productcategoryservice.config;

import com.example.productcategoryservice.model.Role;
import com.example.productcategoryservice.security.JwtAuthenticationTokenFilter;
import com.example.productcategoryservice.security.JwtAuthenticationEntryPoint;
import com.example.productcategoryservice.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtAuthenticationTokenFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/products").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.PUT, "/products").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.DELETE, "/products/{id}").hasAuthority(Role.USER.name())
                .anyRequest().permitAll();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
