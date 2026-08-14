package com.devMoxy.football_quiz.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

// Gates content-mutating requests behind a shared secret header, while leaving
// gameplay POSTs (quiz start/submit, achievement-match guesses, etc.) open for
// anonymous players. Default is protected: any POST/PUT/DELETE/PATCH not in
// GAMEPLAY_ACTION_PATHS requires the header, so a newly added write endpoint
// is safe by default instead of silently unprotected.
@Component
public class WriteAccessInterceptor implements HandlerInterceptor {

    private static final Set<String> GAMEPLAY_ACTION_PATHS = Set.of(
            "/api/career-path/start",
            "/api/career-path/submit",
            "/api/quiz/start",
            "/api/quiz/submit",
            "/api/achievement-match/start",
            "/api/achievement-match/guess",
            "/api/achievement-match/lifeline"
    );

    private static final String API_KEY_HEADER = "X-API-Key";

    @Value("${api.write-key}")
    private String writeKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        if ("GET".equals(method) || "OPTIONS".equals(method) || "HEAD".equals(method)) {
            return true;
        }

        if (GAMEPLAY_ACTION_PATHS.contains(request.getRequestURI())) {
            return true;
        }

        String providedKey = request.getHeader(API_KEY_HEADER);
        if (writeKey != null && writeKey.equals(providedKey)) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\":\"Missing or invalid API key\"}");
        return false;
    }
}
