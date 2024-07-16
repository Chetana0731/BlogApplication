package com.chetana.Blog.Application.DTO;

import com.chetana.Blog.Application.Entities.Category;
import com.chetana.Blog.Application.Entities.Comment;
import com.chetana.Blog.Application.Entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private int postId;
    private String postTitle;
    private String content;
    private String imageName = "default image";
    private Date createdDate = new Date();
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> comments;



}
