package com.chetana.Blog.Application.Repository;

import com.chetana.Blog.Application.Entities.Category;
import com.chetana.Blog.Application.Entities.Post;
import com.chetana.Blog.Application.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByPostTitleContaining(String keyword);

/*    @Query("select p from Post p where post_title like : key")
    List<Post> findByPostTitleContaining(@Param("key") String keyword);*/
}
