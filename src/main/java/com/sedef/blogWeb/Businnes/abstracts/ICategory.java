package com.sedef.blogWeb.Businnes.abstracts;
import Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.Category;
import com.sedef.blogWeb.Request.CategoryRequest;


import java.util.List;

public interface ICategory {

    Category addCategory(CategoryRequest categoryRequest) throws NotFoundException;

    void deleteCategory(int categoryId) throws NotFoundException;

    Category updateCategory(CategoryRequest categoryRequest) throws NotFoundException;

    List<Category> getAllCategories();

    Category getOneCategoryById(int id) throws NotFoundException;
}
