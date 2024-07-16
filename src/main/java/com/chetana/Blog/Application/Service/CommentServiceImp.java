package com.chetana.Blog.Application.Service;

import com.chetana.Blog.Application.DTO.CommentDto;
import com.chetana.Blog.Application.DTO.PostDto;
import com.chetana.Blog.Application.Entities.Comment;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Entities.User;
import com.chetana.Blog.Application.Exceptions.ResourceNotFoundException;
import com.chetana.Blog.Application.Repository.CommentRepo;
import com.chetana.Blog.Application.Repository.PostRepo;
import com.chetana.Blog.Application.Repository.UserRepo;
import com.chetana.Blog.Application.Utils.CommentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public void createComment(Integer postId, CommentDto commentDto) {
       // User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Post post =  postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        Comment comment =  modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
      //  comment.setUser(user);
        commentRepo.save(comment);
    }

    @Override
    public void updateComment(Integer commentId, CommentDto commentDto) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        comment.setComment(commentDto.getComment());
        commentRepo.save(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {

        List<Comment> comments = commentRepo.findAll();
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    /*@Override
    public List<Comment> getAllCommentsByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Comment> comments = commentRepo.findByUser(user);
        return comments;
    }*/

    @Override
    public List<CommentDto> getAllCommentsOnPost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        List<Comment> comments = commentRepo.findByPost(post);
        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        commentRepo.delete(comment);
    }


}
