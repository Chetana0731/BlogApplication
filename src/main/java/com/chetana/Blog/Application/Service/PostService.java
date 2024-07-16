package com.chetana.Blog.Application.Service;

import com.chetana.Blog.Application.DTO.CategoryDto;
import com.chetana.Blog.Application.DTO.PostDto;
import com.chetana.Blog.Application.DTO.UserDto;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Utils.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    void deletePost(Integer postId);
    List<PostDto> searchPost(String keyword);
    PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize);
    PostResponse getPostByCategory(Integer  categoryId,Integer pageNumber, Integer pageSize);
}
