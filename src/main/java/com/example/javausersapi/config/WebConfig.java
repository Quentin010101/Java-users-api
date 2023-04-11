package com.example.javausersapi.config;

import com.example.javausersapi.repository.RequestRepository;
import com.example.javausersapi.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private UserRepository userRepository;
    private RequestRepository requestRepository;

    public WebConfig(UserRepository userRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiKeyInterceptor(userRepository, requestRepository))
                .addPathPatterns("/api/user/**");
    }

}