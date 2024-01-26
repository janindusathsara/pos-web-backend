package lk.ijse.poswebbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.poswebbackend.entity.Category;
import lk.ijse.poswebbackend.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryEntity) {
        return categoryService.createCategory(categoryEntity);
    }

    @GetMapping("/categories/{id}")
    public Category findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryEntity) {
        return categoryService.updateCategory(id, categoryEntity);
    }
    
}
