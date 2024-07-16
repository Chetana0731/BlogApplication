package com.chetana.Blog.Application.Controller;

import com.chetana.Blog.Application.DTO.PostDto;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Repository.PostRepo;
import com.chetana.Blog.Application.Service.FileService;
import com.chetana.Blog.Application.Service.PostService;
import com.chetana.Blog.Application.Utils.ApiResponse;
import com.chetana.Blog.Application.Utils.AppConstants;
import com.chetana.Blog.Application.Utils.PostResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "blogApplicationApi/blogPost/")
public class BlogPostController {

    @Autowired
    FileService fileService;

    PostService postService;

    @Value("${project.image}")
    String path;

    public BlogPostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createBlogPost/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createBlogPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable  Integer categoryId)
    {
        PostDto savedPost = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @PutMapping("/updateBlogPost/{postId}")
    public ResponseEntity<PostDto> updateBlogPost(@RequestBody PostDto postDto, @PathVariable Integer postId)
    {
        PostDto updatedPost = postService.updatePost(postDto,postId);
        ApiResponse apiResponse = new ApiResponse("Blog post created successfully",true);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/getBlogPost/{postId}")
    public ResponseEntity<PostDto> getPostDetails(@PathVariable Integer postId)
    {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/getBlogPost")
    public ResponseEntity<PostResponse> getAllPostDetails(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)
    {
        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize)
    {
        PostResponse postResponse = postService.getPostByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize)
    {
        PostResponse postResponse = postService.getPostByCategory(categoryId,pageNumber,pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Blog post delete successfully",true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/searchPost/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByKeyword(@PathVariable String keyword)
    {
        List<PostDto> posts = postService.searchPost(keyword);
        //ApiResponse apiResponse = new ApiResponse("Blog post delete successfully",true);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //update file name to the db
    @PostMapping("/uploadImage/{postId}")
    public ResponseEntity<PostDto> uploadImage(
                                                   @RequestParam(name = "image") MultipartFile file,
                                                   @PathVariable Integer postId) throws IOException {
        PostDto postDto  = postService.getPostById(postId);
        String fileName  = fileService.uploadFile(path,file);
        postDto.setImageName(fileName);
        PostDto updatedPost = postService.updatePost(postDto,postId);
        //ApiResponse apiResponse = new ApiResponse("Filename :"+fileName+"uploaded successfully",true);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    //Get file from the db
    @GetMapping(value = "/downLoadImage/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getFile(
            @PathVariable String imageName,HttpServletResponse response) throws IOException {

        InputStream resource = fileService.getResource(path,imageName);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
