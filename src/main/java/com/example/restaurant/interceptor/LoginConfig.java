package com.example.restaurant.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
        InterceptorRegistration registration1 = registry.addInterceptor(new CustomerLoginInterceptor());
        registration.addPathPatterns("/**");
        registration1.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/",
                "/login",
                "/customer",
                "/customerIndex",
                "/lookingFor",
                "/orderedMenu",
                "shoppingCart",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css"
        );
        registration1.excludePathPatterns(
                "/",
                "/login",
                "/customer",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css"
        );
    }
}
