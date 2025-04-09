package com.ecommerce.electronicshop.controllers;


import com.ecommerce.electronicshop.dtos.ApiResponseMessage;
import com.ecommerce.electronicshop.dtos.ImageResponse;
import com.ecommerce.electronicshop.dtos.PageableResponse;
import com.ecommerce.electronicshop.dtos.UserDto;
import com.ecommerce.electronicshop.services.FileService;
import com.ecommerce.electronicshop.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    private Logger  logger = LoggerFactory.getLogger(UserController.class);


    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        logger.info("Create user request received");

        UserDto userDto1 = userService.createUser(userDto);

        logger.info("User created");

        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserDto userDto) {

        logger.info("Update user request for ID: {}", userId);

        UserDto updatedUserDto = userService.updateUser(userDto, userId);

        logger.info("User updated");

        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) throws IOException {

        logger.info("Delete user request for ID: {}", userId);

        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("User is deleted successfully").success(true).status(OK).build();

        logger.info("User deleted");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // get all
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,

            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir


    ) {
        logger.info("Get all users request");

        return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);

    }

    //get Single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {

        logger.info("Get user by ID: {}", userId);

        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    // get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

        logger.info("Get user by email: {}", email);

        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }


    // search user
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {

        logger.info("Search users with keywords: {}", keywords);

        return new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);
    }

    //Upload  user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage( @RequestParam("userImage")MultipartFile image,@PathVariable String userId) throws IOException {

            logger.info("Upload image request for user ID: {}", userId);

            String imageName =  fileService.uploadFile(image, imageUploadPath);
            UserDto user = userService.getUserById(userId);
            user.setImageName(imageName);
            UserDto userDto = userService.updateUser(user, userId);
            ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();

        logger.info("Image uploaded for user ID: {}", userId);

            return new ResponseEntity<>(imageResponse, CREATED);
    }


//    serve user image
            @GetMapping("/image/{userId}")
            public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {

                logger.info("Serve image request for user ID: {}", userId);

                UserDto  user = userService.getUserById(userId);
                logger.info("User image name : {} ", user.getImageName());
                InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());

                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                StreamUtils.copy(resource, response.getOutputStream());
            }



}

