package com.chetana.Blog.Application.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //1.get token
        String requestToken = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if(request != null && requestToken.startsWith("Bearer")){
             token = requestToken.substring(7);
             try {
                 username = jwtTokenHelper.getUserNameFromToken(token);
             }catch(IllegalArgumentException e)
             {
                 System.out.println("Unable to get Jwt token");
             }catch (ExpiredJwtException e)
             {
                 System.out.println("Jwt token has expired");
             }catch (MalformedJwtException)
             {
                 System.out.println("Invalid JWt");
             }
        }else{
            System.out.println("Jwt token does not begin with bearer");
        }

        //once we got the token now will validate it
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtTokenHelper.validateToken(token,userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken()
            }else{
                System.out.println("Invalid jwt token");
            }
        }else{

        }
    }
}
