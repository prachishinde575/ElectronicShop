package com.ecommerce.electronicshop.services.impl;

import com.ecommerce.electronicshop.dtos.CategoryDto;
import com.ecommerce.electronicshop.dtos.PageableResponse;
import com.ecommerce.electronicshop.entities.Category;
import com.ecommerce.electronicshop.exceptions.ResourceNotFoundException;
import com.ecommerce.electronicshop.helper.Helper;
import com.ecommerce.electronicshop.repositories.CategoryRepository;
import com.ecommerce.electronicshop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper ;


    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        Category savedcategory = categoryRepository.save(category);

        return mapper.map(savedcategory, CategoryDto.class);
    }


    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        //get category of given id
        Category category =categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception"));

        // update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        return mapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {

        Category category =categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception"));
        categoryRepository.delete(category);


    }

    @Override
    public PageableResponse<CategoryDto> getall(int pageNumber, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);

        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {


        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception"));

        return mapper.map(category, CategoryDto.class);


    }
}
