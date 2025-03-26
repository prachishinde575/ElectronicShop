package com.ecommerce.electronicshop.services;

import com.ecommerce.electronicshop.dtos.UserDto;
import com.ecommerce.electronicshop.entities.User;

import java.util.List;

public interface UserService {

    //create

    UserDto createUser(UserDto userDto);




    //update
    UserDto updateUser(UserDto userDto, String userId);



    //delete
    void deleteUser(String userId);



    //get all user
    List<UserDto> getAllUser();


    //get single user by id
    UserDto getUserById(String userId);



    //get single user by email
    UserDto getUserByEmail(String email);


    //search user

    List<UserDto> searchUser(String keyword);



    //Other user specific  features











}
