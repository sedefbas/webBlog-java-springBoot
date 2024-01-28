package com.sedef.blogWeb.Controller;

import Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.concretes.CategoryService;
import com.sedef.blogWeb.Model.Category;
import com.sedef.blogWeb.Request.CategoryRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/add")
    public Category addCategory(@RequestBody CategoryRequest categoryRequest) throws NotFoundException {
      return categoryService.addCategory(categoryRequest);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(int categoryId) throws NotFoundException {
        categoryService.deleteCategory(categoryId);
    }

     @GetMapping("/getAll")
     public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
     }

     @GetMapping("/getOne")
    public Category getOneCategoryById(int id) throws NotFoundException {
        return categoryService.getOneCategoryById(id);
     }

    @PostMapping("/update")
    public Category updateCategory(@RequestBody CategoryRequest categoryRequest) throws NotFoundException {
        return categoryService.updateCategory(categoryRequest);
    }

}
