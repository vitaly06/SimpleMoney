package ru.oksei.talisman.simpleMoney.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.Category;
import ru.oksei.talisman.simpleMoney.Repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllById(int id){
        return categoryRepository.findAllByPerson_PersonId(id);
    }
    @Transactional
    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getCategoriesByType(String type, int personId){
        return categoryRepository.findAllByCategoryTypeAndPerson_PersonId(type, personId);
    }

    public Category getCategoryById(int id){
        return categoryRepository.findByCategoryId(id);
    }

    @Transactional
    public void updateCategory(int categoryId, Category category){
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            categoryRepository.save(existingCategory);
        }
    }

    @Transactional
    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
}
