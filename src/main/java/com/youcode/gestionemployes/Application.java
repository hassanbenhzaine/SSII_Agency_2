package com.youcode.gestionemployes;

import com.youcode.gestionemployes.controller.filter.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// components scanner
@Configuration @ComponentScan
public class Application {
}
