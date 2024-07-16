package com.chetana.Blog.Application.Service;


import com.chetana.Blog.Application.DTO.PostDto;
import com.chetana.Blog.Application.Entities.Category;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Entities.User;
import com.chetana.Blog.Application.Exceptions.ResourceNotFoundException;
import com.chetana.Blog.Application.Repository.CategoryRepo;
import com.chetana.Blog.Application.Repository.PostRepo;
import com.chetana.Blog.Application.Repository.UserRepo;
import com.chetana.Blog.Application.Utils.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsServiceImp implements PostService {

    PostRepo postRepo;
    ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public PostsServiceImp(PostRepo postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        Post post = modelMapper.map(postDto,Post.class);
      //  post.setImageName("default image");
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        post.setUser(user);
        post.setCategory(category);
        Post newPost =  postRepo.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post_id",postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setImageName(postDto.getImageName());
//        post.setCategory(modelMapper.map(postDto.getCategory(),Category.class));
//        post.setUser(modelMapper.map(postDto.getUser(),User.class));
        post.setContent(postDto.getContent());
        Post updatedPost =  postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post_id",postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy , String sortDir) {
       // List<Post> posts = postRepo.findAll();
        Sort sort = Sort.by(sortBy).ascending();
        if(sortDir.equalsIgnoreCase("dsc")) sort = Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        long totalElements = pagePost.getTotalElements();
        long totalPages = pagePost.getTotalPages();
        boolean lastPage = pagePost.isLast();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(totalElements);
        postResponse.setTotalPages(totalPages);
        postResponse.setLastPage(lastPage);
        return postResponse;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post_id",postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> specificpost = postRepo.findByPostTitleContaining(keyword);
        List<PostDto> posts = specificpost.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return posts;
    }


    public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize)
    {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> page = postRepo.findAll(pageable);
        List<Post> postsByUser = page.getContent();
        List<PostDto> posts = postsByUser.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        long totalElements = page.getTotalElements();
        long totalPages = page.getTotalPages();
        boolean lastPage = page.isLast();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(posts);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(totalElements);
        postResponse.setTotalPages(totalPages);
        postResponse.setLastPage(lastPage);
        return postResponse;
    }

    public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> page = postRepo.findAll(pageable);
        List<Post> postsByUser = page.getContent();
        List<PostDto> posts = postsByUser.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        long totalElements = page.getTotalElements();
        long totalPages = page.getTotalPages();
        boolean lastPage = page.isLast();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(posts);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(totalElements);
        postResponse.setTotalPages(totalPages);
        postResponse.setLastPage(lastPage);
        return postResponse;
    }


}
