package com.chetana.Blog.Application.Service;

import com.chetana.Blog.Application.DTO.CategoryDto;
import com.chetana.Blog.Application.Entities.Category;
import com.chetana.Blog.Application.Exceptions.ResourceNotFoundException;
import com.chetana.Blog.Application.Repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService{

    CategoryRepo categoryRepo;
    ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepo.save(modelMapper.map(categoryDto,Category.class));
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category updateCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Catgory","Id",categoryId));
        //updateCategory.setCategoryId(categoryDto.getCategoryId());
        updateCategory.setCategoryName(categoryDto.getCategoryName());
        updateCategory.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = categoryRepo.save(updateCategory);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category requiredCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Catgory","Id",categoryId));
        return modelMapper.map(requiredCategory,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Catgory","Id",categoryId));
        categoryRepo.delete(category);
    }
}
