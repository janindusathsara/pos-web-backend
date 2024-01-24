package lk.ijse.poswebbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.entity.Category;
import lk.ijse.poswebbackend.repository.CategoryRepository;
import lk.ijse.poswebbackend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Long id, Category categoryEntity) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            existingCategory.setName(categoryEntity.getName());
            return categoryRepository.save(existingCategory);
        } else {
            return null;
        }
    }
    
}
