package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.Category;
import ru.oksei.talisman.simpleMoney.Services.CategoryService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/all/{userId}")
    public List<Category> getAll(@PathVariable("userId") int id) {
        return categoryService.findAllById(id);
    }
}
