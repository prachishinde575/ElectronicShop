package com.ecommerce.electronicshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController 
public class HomeController {

        @GetMapping
        public String Testing() {

            return "Welcome to electronics shop";

    }

}
