package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.Category;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Services.CategoryService;
import ru.oksei.talisman.simpleMoney.Services.PersonService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    private PersonService personService;
    @Autowired
    public CategoryController(CategoryService categoryService, PersonService personService) {
        this.personService = personService;
        this.categoryService = categoryService;
    }
    @GetMapping("/all/{userId}")
    public List<Category> getAll(@PathVariable("userId") int id) {
        return categoryService.findAllById(id);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/addCategory")
    public void addCategory(@ModelAttribute Category category,
                            @RequestParam("personId") int personId) {

        Person person = personService.getPerson(personId);

        // Установить связь между Category и Person
        category.setPerson(person);
        categoryService.addCategory(category);
    }

    // Категории по типам (траты - доходы)
    @GetMapping("/getСostCategories/{type}-{personId}")
    public List<Category> getCostCategories(@PathVariable("type") String type,
                                            @PathVariable("personId") int personId) {
        return categoryService.getCategoriesByType(type, personId);
    }

    @PostMapping("/updateCategory")
    public void updateCategory(@ModelAttribute Category category,
                               @RequestParam("categoryId") int categoryId){
        categoryService.updateCategory(categoryId, category);
    }

    @PostMapping("/deleteCategory")
    public void deleteCategory(@RequestParam("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
