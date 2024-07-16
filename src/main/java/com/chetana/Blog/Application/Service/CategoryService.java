package com.chetana.Blog.Application.Service;

import com.chetana.Blog.Application.DTO.CategoryDto;
import com.chetana.Blog.Application.Entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    CategoryDto getCategory(Integer categoryId);
    List<CategoryDto> getCategories();
    void deleteCategory(Integer categoryId);

}
