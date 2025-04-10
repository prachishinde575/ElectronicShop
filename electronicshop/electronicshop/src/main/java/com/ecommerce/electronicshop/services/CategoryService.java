package com.ecommerce.electronicshop.services;

import com.ecommerce.electronicshop.dtos.CategoryDto;
import com.ecommerce.electronicshop.dtos.PageableResponse;

public interface CategoryService {

    // Create
    CategoryDto create(CategoryDto categoryDto);


    //Update
    CategoryDto update(CategoryDto categoryDto, String categoryId);


    //delete
    void delete (String categoryId);


    //get all users
    PageableResponse<CategoryDto> getall(int pageNumber, int pageSize, String sortBy, String sortDir);


    //get single category detail
    CategoryDto get(String categoryId);


    //search




















}
