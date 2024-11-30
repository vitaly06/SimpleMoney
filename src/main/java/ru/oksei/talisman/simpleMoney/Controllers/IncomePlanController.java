package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Services.IncomePlanService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/incomePlan")
public class IncomePlanController {
    @Autowired
    private IncomePlanService incomePlanService;

    @PostMapping("/addIncomePlan")
    public void addIncomePlan(@ModelAttribute IncomePlan incomePlan) {
        incomePlanService.saveIncomePlan(incomePlan);
    }

    @GetMapping("/{month}-{year}-{userId}")
    public List<IncomePlan> getIncomePlanForUser(@PathVariable("month") String month,
                                                 @PathVariable("userId") int userId,
                                                 @PathVariable("year") int year) {
        return incomePlanService.getIncomePlansForUser(month, year, userId);
    }
}
