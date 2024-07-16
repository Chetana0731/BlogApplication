package com.chetana.Blog.Application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "description")
    private String categoryDescription;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> post;
}
