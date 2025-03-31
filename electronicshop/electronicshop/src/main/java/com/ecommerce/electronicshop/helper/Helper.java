package com.ecommerce.electronicshop.helper;

import com.ecommerce.electronicshop.dtos.PageableResponse;
import com.ecommerce.electronicshop.dtos.UserDto;
import com.ecommerce.electronicshop.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type)
    {
        List<U> entity = page.getContent(); // Fix: Added semicolon
        List<V> dtoList = entity.stream().map(object -> new ModelMapper().map(object,type)).collect(Collectors.toList());


        PageableResponse<V> response = new PageableResponse<>();
        response.setContent((List<UserDto>) dtoList);                       /// added by suggestions (List<UserDto>) for error resolution
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setPageNumber(page.getNumber());
        response.setLastPage(page.isLast());

        return  response;


    }


}
