package com.sedef.blogWeb.Businnes.concretes;
import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.abstracts.IsubCategory;
import com.sedef.blogWeb.Model.Category;
import com.sedef.blogWeb.Model.SubCategory;
import com.sedef.blogWeb.Repository.SubCategoryRepository;
import com.sedef.blogWeb.Request.SubCategoryRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class SubCategoryService implements IsubCategory {

    SubCategoryRepository subCategoryRepository;
    CategoryService categoryService;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public SubCategory addSubCategory(SubCategoryRequest subCategoryRequest) throws NotFoundException {
        if (subCategoryRequest == null) {
            throw new NotFoundException("subCategoryRequest is null");
        }

        if (subCategoryRequest.getCategoryId() == 0) {
            throw new NotFoundException("categoryId is 0");
        }

        if (subCategoryRequest.getName() == null) {
            throw new NotFoundException("name is null");
        }

        Category category = categoryService.getOneCategoryById(subCategoryRequest.getCategoryId());

        SubCategory subCategory = new SubCategory();
        subCategory.setId(0);
        subCategory.setName(subCategoryRequest.getName());
        subCategory.setCategory(category);

        return subCategoryRepository.save(subCategory);
    }


    @Override
    public SubCategory updateSubCategory(SubCategoryRequest subCategoryRequest, int id) throws NotFoundException {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        Optional<Category> optionalCategory = Optional.ofNullable(categoryService.getOneCategoryById(subCategoryRequest.getCategoryId()));

        if (optionalSubCategory.isPresent() && optionalCategory.isPresent()) {
            SubCategory subCategory = optionalSubCategory.get();
            Category category = optionalCategory.get();

            if (subCategoryRequest.getName() != null && !subCategoryRequest.getName().isEmpty() &&
                    subCategoryRequest.getCategoryId() != 0) {
                subCategory.setName(subCategoryRequest.getName());
                subCategory.setCategory(category);
                return subCategoryRepository.save(subCategory);
            } else {
                if (subCategoryRequest.getName() != null && !subCategoryRequest.getName().isEmpty()) {
                    subCategory.setName(subCategoryRequest.getName());
                    return subCategoryRepository.save(subCategory);
                }

                if (subCategoryRequest.getCategoryId() != 0) {
                    subCategory.setCategory(category);
                    return subCategoryRepository.save(subCategory);
                }
            }

        }
        throw new NotFoundException("CategoryRequest name cannot be null or empty.");
    }


    @Override
    public SubCategory getOneSubCategoryById(int id) throws NotFoundException {
        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(id);

        if (subCategoryOptional.isPresent()) {
            return subCategoryOptional.get();
        } else {
            throw new NotFoundException("ID'si " + id + " olan SubCategory bulunamadı");
        }
    }


    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void deleteSubCategoryById(int id) throws NotFoundException {
     if(subCategoryRepository.existsById(id)) {
      subCategoryRepository.deleteById(id);}
      else
        throw new NotFoundException("Category with ID not found: " + id);

    }
}