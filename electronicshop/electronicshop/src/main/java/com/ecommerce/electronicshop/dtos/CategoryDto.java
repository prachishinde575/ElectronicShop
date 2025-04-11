package com.ecommerce.electronicshop.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {

    private String categoryId;

    @NotBlank(message  ="title is requird")
    @Size(min = 4, message = "title must be minimum 4 characters")
    private String title;

    @NotBlank(message = "Description required !")
    private String description;

    @NotBlank(message = "cover image required !")
    private String coverImage;






}
