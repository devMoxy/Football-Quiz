package com.devMoxy.football_quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${FRONTEND_URL:}")
    private String frontendUrl;

    private final WriteAccessInterceptor writeAccessInterceptor;

    public CorsConfig(WriteAccessInterceptor writeAccessInterceptor) {
        this.writeAccessInterceptor = writeAccessInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        List<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("http://localhost:[*]");
        if (frontendUrl != null && !frontendUrl.isBlank()) {
            allowedOriginPatterns.add(frontendUrl);
        }

        registry.addMapping("/api/**")
                .allowedOriginPatterns(allowedOriginPatterns.toArray(new String[0]))
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization", "X-API-Key")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(writeAccessInterceptor).addPathPatterns("/api/**");
    }
}