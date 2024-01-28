package com.sedef.blogWeb.Businnes.abstracts;

import Exceptions.NotFoundException;
import com.sedef.blogWeb.Model.SubCategory;
import com.sedef.blogWeb.Request.SubCategoryRequest;

import java.util.List;

public interface IsubCategory {
    SubCategory addSubCategory(SubCategoryRequest subCategoryRequest) throws NotFoundException;
    SubCategory updateSubCategory(SubCategoryRequest subCategoryRequest,int id) throws NotFoundException;
    SubCategory getOneSubCategoryById(int id) throws NotFoundException;
    List<SubCategory> getAllSubCategory();
    void deleteSubCategoryById(int id) throws NotFoundException;
}
