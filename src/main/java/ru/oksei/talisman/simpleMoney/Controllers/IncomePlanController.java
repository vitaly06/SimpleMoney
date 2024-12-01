package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.Category;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Services.CategoryService;
import ru.oksei.talisman.simpleMoney.Services.IncomePlanService;
import ru.oksei.talisman.simpleMoney.Services.PersonService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/incomePlan")
public class IncomePlanController {
    @Autowired
    private IncomePlanService incomePlanService;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addIncomePlan")
    public void addIncomePlan(@ModelAttribute IncomePlan incomePlan,
                              @RequestParam("personId") int personId,
                              @RequestParam("categoryId") int categoryId) {
        Person person = personService.getPerson(personId);
        Category category = categoryService.getCategoryById(categoryId);
        incomePlan.setPerson(person);
        incomePlan.setCategory(category);
        incomePlanService.saveIncomePlan(incomePlan);
    }

    @GetMapping("/{month}-{year}-{userId}")
    public List<IncomePlan> getIncomePlanForUser(@PathVariable("month") String month,
                                                 @PathVariable("userId") int userId,
                                                 @PathVariable("year") int year) {
        return incomePlanService.getIncomePlansForUser(month, year, userId);
    }
}
