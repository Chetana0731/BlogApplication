package com.chetana.Blog.Application.Service;

import com.chetana.Blog.Application.Entities.Roles;
import com.chetana.Blog.Application.Entities.User;
import com.chetana.Blog.Application.Exceptions.ResourceNotFoundException;
import com.chetana.Blog.Application.Exceptions.UnAuthorisedException;
import com.chetana.Blog.Application.DTO.UserDto;
import com.chetana.Blog.Application.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{

    private UserRepo userRepo;
    private ModelMapper modelMapper;

    public UserServiceImp(UserRepo userRepo,ModelMapper modelMapper) {

        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        System.out.println("User Dto--------------->"+userDto.toString());
        System.out.println("User--------------->"+user.toString());
        User savedUser = userRepo.save(dtoToUser(userDto));
        /*User user = dtoToUser(userDto);
        Roles role1 = new Roles(1,"ROLE_ADMIN");
        Roles role2 = new Roles(2,"ROLE_USER");
        List<Roles> roles = new ArrayList<>(Arrays.asList(role1,role2));
        user.setRoles(roles);
        User savedUser = userRepo.save(user);*/
        return userToDto(savedUser) ;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
       // if(userId == 2) throw new UnAuthorisedException(2);
       // Optional<User> user = userRepo.findById(userId);
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserInfo(userDto.getUserInfo());
        User updatedUser = userRepo.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user->userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto,User.class);
        /*User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserInfo(userDto.getUserInfo());*/
        return user;
    }

    private UserDto userToDto(User user)
    {
        UserDto userDto  = modelMapper.map(user,UserDto.class);
        /*UserDto userDto  = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserInfo(user.getUserInfo());*/
        return userDto;
    }
}
