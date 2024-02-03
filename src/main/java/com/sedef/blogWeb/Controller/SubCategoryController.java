package com.sedef.blogWeb.Controller;
import com.sedef.blogWeb.Exceptions.NotFoundException;
import com.sedef.blogWeb.Businnes.concretes.SubCategoryService;
import com.sedef.blogWeb.Model.SubCategory;
import com.sedef.blogWeb.Request.SubCategoryRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/subs")
public class SubCategoryController {
    SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping("/add")
    public SubCategory addSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) throws NotFoundException {
        return  subCategoryService.addSubCategory(subCategoryRequest);
    }
    @PostMapping("/{id}")
    public SubCategory updateCategory(@RequestBody SubCategoryRequest subCategoryRequest,@PathVariable int id) throws NotFoundException {
        return subCategoryService.updateSubCategory(subCategoryRequest,id);
    }
    @GetMapping("/getOne/{id}")
    public  SubCategory getOneSubCategoryById(@PathVariable int id) throws NotFoundException {
        return subCategoryService.getOneSubCategoryById(id);
    }
    @GetMapping("/getAll")
    public List<SubCategory> getAllSubCategory(){
        return subCategoryService.getAllSubCategory();
    }

    @DeleteMapping("/{id}")
    public void deleteSubCategory(@PathVariable int id) throws NotFoundException{
        subCategoryService.deleteSubCategoryById(id);
    }


}
