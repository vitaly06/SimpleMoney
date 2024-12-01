package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.Expense;
import ru.oksei.talisman.simpleMoney.Services.ExpenseService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;


    @GetMapping("/{month}-{year}-{userId}")
    public List<Expense> getExpenseForUser(@PathVariable("month") String month,
                                          @PathVariable("userId") int userId,
                                          @PathVariable("year") int year) {
        return expenseService.getExpenseForUser(month, year, userId);
    }

    @PostMapping("/addExpense")
    public void addIncomePlan(@ModelAttribute Expense expense,
                              @RequestParam("personId") int personId,
                              @RequestParam("categoryId") int categoryId,
                              @RequestParam("accountId") int accountId) {

        expenseService.saveExpense(expense, personId, categoryId, accountId);
    }
}
