package com.chetana.Blog.Application.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 3,message = "User name should be atleast 3 characters")
    private String userName;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10,message = "Password should be minimum 3 charactes and maximum 10 characters!!")
   // @Pattern
    private String password;

    @NotEmpty
    private String userInfo;




}
