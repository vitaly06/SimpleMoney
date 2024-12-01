package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.Category;
import ru.oksei.talisman.simpleMoney.Models.CostPlan;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Services.CategoryService;
import ru.oksei.talisman.simpleMoney.Services.CostPlanService;
import ru.oksei.talisman.simpleMoney.Services.IncomePlanService;
import ru.oksei.talisman.simpleMoney.Services.PersonService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/costPlan")
public class CostPlanController {
    @Autowired
    private CostPlanService costPlanService;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCostPlan")
    public void addCostPlan(@ModelAttribute CostPlan costPlan,
                            @RequestParam("personId") int personId,
                            @RequestParam("categoryId") int categoryId) {
        Person person = personService.getPerson(personId);
        Category category = categoryService.getCategoryById(categoryId);
        costPlan.setPerson(person);
        costPlan.setCategory(category);
        costPlanService.saveCostPlan(costPlan);
    }

    @GetMapping("/{month}-{year}-{userId}")
    public List<CostPlan> getCostPlanForUser(@PathVariable("month") String month,
                                                 @PathVariable("userId") int userId,
                                                 @PathVariable("year") int year) {
        System.out.println(month + "-" + year + "-" + userId);
        return costPlanService.getCostPlansForUser(month, year, userId);
    }
}