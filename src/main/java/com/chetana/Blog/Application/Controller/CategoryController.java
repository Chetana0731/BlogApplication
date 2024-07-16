package com.chetana.Blog.Application.Controller;

import com.chetana.Blog.Application.DTO.CategoryDto;
import com.chetana.Blog.Application.Entities.Category;
import com.chetana.Blog.Application.Service.CategoryService;
import com.chetana.Blog.Application.Utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blogApplicationApi/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //create
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }


    //update
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto category = categoryService.updateCategory(categoryDto,categoryId);
        ApiResponse response = new ApiResponse("Category updated successfully",true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get
    @GetMapping("/getCategory/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
    {
        CategoryDto category = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //getAll
    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryDto>> getCategory()
    {
        List<CategoryDto> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
    {
        categoryService.deleteCategory(categoryId);
        ApiResponse response = new ApiResponse("Category deleted successfully",true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
