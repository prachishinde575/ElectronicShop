package com.ecommerce.electronicshop.services.impl;

import com.ecommerce.electronicshop.dtos.UserDto;
import com.ecommerce.electronicshop.entities.User;
import com.ecommerce.electronicshop.repositories.UserRepository;
import com.ecommerce.electronicshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {      // ALt+Enter   click on userserviceimpl and then click implements all methods and select all methons and ok then direct all methods are come

@Autowired
private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // generate unique id in string format
       String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

       // Dto---> Entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        // Entity --> Dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        User user =userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found with given id! "));
        user.setName(userDto.getName());
        // email update
        user.setAbout(userDto.getAbout());
        user.setGender(user.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        //Save data
        User updateduser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updateduser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found with given id!"));
        //delete user
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users=userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user->entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto getUserbyId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found with given id!"));

        return entityToDto(user);
    }


    @Override
    public UserDto getUserbyEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with given email id and password !"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaning(keyword);
        List<UserDto> dtoList = users.stream().map(user->entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }



    private UserDto entityToDto(User savedUser) {

    UserDto userDto =UserDto.builder()
            .userId(savedUser.getUserId())
            .name(savedUser.getName())
            .email(savedUser.getEmail())
            .password(savedUser.getPassword())
            .about(savedUser.getAbout())
            .gender(savedUser.getGender())
            .imageName(savedUser.getImageName()).build();

    return userDto;
    }

    private User dtoToEntity(UserDto userDto) {

       User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
               .password(userDto.getPassword())
               .about(userDto.getAbout())
               .gender(userDto.getGender())
               .imageName(userDto.getImageName())
               .build();

        return user;
    }

}