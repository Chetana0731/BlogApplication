package com.chetana.Blog.Application.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private int categoryId;

    @NotEmpty
    @Size(min =3)
    private String categoryName;


    private String categoryDescription;
}
