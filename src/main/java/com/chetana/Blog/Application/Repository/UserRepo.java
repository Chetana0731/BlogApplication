package com.chetana.Blog.Application.Repository;

import com.chetana.Blog.Application.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
