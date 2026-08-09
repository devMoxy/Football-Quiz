package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.exception.DuplicatePlayerAchievementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldError().getField() + " is invalid";
    }

    @ExceptionHandler(DuplicatePlayerAchievementException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicatePlayerAchievement(DuplicatePlayerAchievementException ex) {
        return ex.getMessage();
    }
}