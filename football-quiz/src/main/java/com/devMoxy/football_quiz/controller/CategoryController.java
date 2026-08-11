package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.CategoryCreateDTO;
import com.devMoxy.football_quiz.dto.CategoryDTO;
import com.devMoxy.football_quiz.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public List<CategoryDTO> categories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/api/categories")
    public CategoryDTO postCategory(@Valid @RequestBody CategoryCreateDTO dto){
        return categoryService.createCategory(dto);
    }
}
