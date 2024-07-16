package com.chetana.Blog.Application.Repository;

import com.chetana.Blog.Application.Entities.Comment;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

   // List<Comment> findByUser(User user);
    List<Comment> findByPost(Post post);
    //List<Comment> findByUserAndPost(User user, Post post);
}
