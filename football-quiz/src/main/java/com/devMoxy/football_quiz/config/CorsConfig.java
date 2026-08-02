package com.devMoxy.football_quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${FRONTEND_URL:}")
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("http://localhost:3000");
        if (frontendUrl != null && !frontendUrl.isBlank()) {
            allowedOrigins.add(frontendUrl);
        }

        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins.toArray(new String[0]))
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);
    }
}
