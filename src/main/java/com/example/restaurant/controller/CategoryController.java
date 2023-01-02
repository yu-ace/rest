package com.example.restaurant.controller;

import com.example.restaurant.model.Category;
import com.example.restaurant.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @ResponseBody
    @RequestMapping(path = "/category",method = RequestMethod.GET)
    public Category categoryName(int categoryId){
        Category categoryById = categoryService.getCategoryById(categoryId);
        return categoryById;
    }
}
