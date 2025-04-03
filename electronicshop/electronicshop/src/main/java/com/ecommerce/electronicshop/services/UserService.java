package com.ecommerce.electronicshop.services;

import com.ecommerce.electronicshop.dtos.PageableResponse;
import com.ecommerce.electronicshop.dtos.UserDto;
import com.ecommerce.electronicshop.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    //create

    UserDto createUser(UserDto userDto);




    //update
    UserDto updateUser(UserDto userDto, String userId);



    //delete
    void deleteUser(String userId) throws IOException;



    //get all user
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);


    //get single user by id
    UserDto getUserById(String userId);



    //get single user by email
    UserDto getUserByEmail(String email);


    //search user

    List<UserDto> searchUser(String keyword);



    //Other user specific  features











}
