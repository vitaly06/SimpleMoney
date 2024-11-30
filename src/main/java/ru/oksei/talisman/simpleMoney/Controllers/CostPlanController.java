package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.CostPlan;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Services.CostPlanService;
import ru.oksei.talisman.simpleMoney.Services.IncomePlanService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/costPlan")
public class CostPlanController {
    @Autowired
    private CostPlanService costPlanService;

    @PostMapping("/addCostPlan")
    public void addCostPlan(@ModelAttribute CostPlan costPlan) {
        costPlanService.saveCostPlan(costPlan);
    }

    @GetMapping("/{month}-{year}-{userId}")
    public List<CostPlan> getCostPlanForUser(@PathVariable("month") String month,
                                                 @PathVariable("userId") int userId,
                                                 @PathVariable("year") int year) {
        return costPlanService.getCostPlansForUser(month, year, userId);
    }
}