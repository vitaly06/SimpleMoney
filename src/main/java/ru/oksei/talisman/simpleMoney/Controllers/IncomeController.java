package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.*;
import ru.oksei.talisman.simpleMoney.Services.IncomeService;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;


    @GetMapping("/{month}-{year}-{userId}")
    public List<Income> getIncomeForUser(@PathVariable("month") String month,
                                             @PathVariable("userId") int userId,
                                             @PathVariable("year") int year) {
        return incomeService.getIncomeForUser(month, year, userId);
    }

    @PostMapping("/addIncome")
    public void addIncomePlan(@ModelAttribute Income income,
                              @RequestParam("personId") int personId,
                              @RequestParam("categoryId") int categoryId,
                              @RequestParam("accountId") int accountId) {
        incomeService.saveIncome(income, personId, categoryId, accountId);
    }
}
