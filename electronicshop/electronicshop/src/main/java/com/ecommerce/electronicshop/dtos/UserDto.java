package com.ecommerce.electronicshop.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserDto {


    private String userId;

    private String name;

    private String email;

    private String password;

    private String gender;

    private String about;

    private String imageName;





















}
