package com.example.backend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.backend.service.impl.AuthServiceImpl;
import com.example.backend.utils.AuthUtils;

@Configuration
public class AuthConfig {

    @Bean
    public FilterRegistrationBean<AuthUtils> authFilter(AuthServiceImpl authService) {
        FilterRegistrationBean<AuthUtils> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthUtils(authService));
        registrationBean.addUrlPatterns("/api/*"); // Apply filter to specific URL patterns
        return registrationBean;
    }

}