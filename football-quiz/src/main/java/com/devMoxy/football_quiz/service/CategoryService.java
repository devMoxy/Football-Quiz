package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.CategoryDTO;
import com.devMoxy.football_quiz.entity.Category;
import com.devMoxy.football_quiz.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new ArrayList<>();

        for(Category c: categories){
            CategoryDTO dto = convertToDTO(c);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private CategoryDTO convertToDTO(Category category){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
