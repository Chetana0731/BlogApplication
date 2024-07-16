package com.chetana.Blog.Application.Service;

import com.chetana.Blog.Application.DTO.CommentDto;
import com.chetana.Blog.Application.DTO.PostDto;
import com.chetana.Blog.Application.Entities.Comment;
import com.chetana.Blog.Application.Utils.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void createComment(Integer postId,CommentDto commentDto);
    void updateComment(Integer commentId, CommentDto commentDto);
    List<CommentDto> getAllComments();
    List<CommentDto> getAllCommentsOnPost(Integer postId);
    void deleteComment(Integer commentId);

    //deleteCommentByParticularUser
    //delete comment on particular post;
    //delete comment on particular post by particular user
}
