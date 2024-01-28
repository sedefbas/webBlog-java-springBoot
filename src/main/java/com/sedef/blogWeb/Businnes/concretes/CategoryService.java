package com.sedef.blogWeb.Businnes.concretes;

import Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.abstracts.ICategory;
import com.sedef.blogWeb.Model.Category;
import com.sedef.blogWeb.Repository.CategoryRepository;
import com.sedef.blogWeb.Request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategory {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category addCategory(CategoryRequest categoryRequest) throws NotFoundException {
        if (categoryRequest == null || categoryRequest.getName() == null || categoryRequest.getName().isEmpty()) {
            throw new NotFoundException("CategoryRequest name cannot be null or empty.");
        }
        Category category = new Category();
        category.setId(0);
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int categoryId) throws NotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new NotFoundException("Category with ID not found: " + categoryId);
        }
    }


    @Override
    public Category updateCategory(CategoryRequest categoryRequest) throws NotFoundException {
        if (categoryRequest == null || categoryRequest.getName() == null || categoryRequest.getName().isEmpty()) {
            throw new NotFoundException("CategoryRequest name cannot be null or empty.");
        }
        Category category = new Category();
        category.setId(0);
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category getOneCategoryById(int id) throws NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));
    }
}
