package com.ecommerce.electronicshop.dtos;

import com.ecommerce.electronicshop.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserDto {


    private String userId ;

    @Size( min = 3, max = 15, message = "invalid name !!" )
    private String name;


   // @Email(message = "invalid user Email !")

    //@Pattern(regexp ="" message = "Invali user")
    @Pattern(regexp = "^[a-z0-9][-a-z8-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "Invalid username: only alphanumeric, 5-10 chars")

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message ="invalid Password")
    private String password;

    @Size( min = 4, max=10, message = "Invalid gender !")
    private String gender;

    @NotBlank(message = "Invalid about !" )
    private String about;

    // pattarn
    // custom validator
    @ImageNameValid
    private String imageName;





















}
