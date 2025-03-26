package com.ecommerce.electronicshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString



@Entity
@Table( name = "Users")
public class User {

@Id
private String userId;

@Column(name = "user_name")
private String name;

@Column(name = "user_email", unique = true)
private String email;

@Column(name = "user_password", length = 15)
private String password;

private String gender;

@Column(length = 500)
private String about;

@Column(name= "user_image_name")
private String imageName;


}
