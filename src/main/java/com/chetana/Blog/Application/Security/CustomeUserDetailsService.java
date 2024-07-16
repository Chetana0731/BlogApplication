package com.chetana.Blog.Application.Security;

import com.chetana.Blog.Application.Entities.User;
import com.chetana.Blog.Application.Exceptions.ResourceNotFoundException;
import com.chetana.Blog.Application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;

    //fetch user details by email from db
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user","email",-1));
        System.out.println("DIsplaying user roles");
        System.out.println(user.getRoles());
        return user;
    }
}
