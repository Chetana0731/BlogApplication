package com.chetana.Blog.Application.Repository;

import com.chetana.Blog.Application.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
