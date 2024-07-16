package com.chetana.Blog.Application.Controller;

import com.chetana.Blog.Application.DTO.CommentDto;
import com.chetana.Blog.Application.Entities.Comment;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Entities.User;
import com.chetana.Blog.Application.Service.CommentServiceImp;
import com.chetana.Blog.Application.Utils.ApiResponse;
import com.chetana.Blog.Application.Utils.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "blogApplicationApi/")
public class CommentController {

    @Autowired
    CommentServiceImp commentServiceImp;


    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<String> createComment(@PathVariable Integer postId, @RequestBody CommentDto commentDto)
    {
        commentServiceImp.createComment(postId,commentDto);
        return new ResponseEntity<>("Commented Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Integer commentId, @RequestBody CommentDto commentDto)
    {
        commentServiceImp.updateComment(commentId, commentDto);
        return new ResponseEntity<>("Comment updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<CommentDto>> getComments()
    {
        return new ResponseEntity<>(commentServiceImp.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/getComments/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Integer postId)
    {
        return new ResponseEntity<>(commentServiceImp.getAllCommentsOnPost(postId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId)
    {
        commentServiceImp.deleteComment(commentId);
        return new ResponseEntity<>("Deleted comment", HttpStatus.OK);
    }
}
