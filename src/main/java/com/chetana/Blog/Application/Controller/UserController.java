package com.chetana.Blog.Application.Controller;

import com.chetana.Blog.Application.Utils.ApiResponse;
import com.chetana.Blog.Application.DTO.UserDto;
import com.chetana.Blog.Application.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blogApplicationApi/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Create user
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto)
    {
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }

    //update user
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId)
    {
        UserDto updatedUser = userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //find a user
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId)
    {
       UserDto userDto =  userService.getUserById(userId);
       return ResponseEntity.ok(userDto);
    }


    //get list of users
    @GetMapping("/getUser")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> users =  userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //delete a user
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
    {
        userService.deleteUser(uid);
        ApiResponse apiResponse = new ApiResponse("User deleted successfully",true);
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
