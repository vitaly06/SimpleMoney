package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.*;
import ru.oksei.talisman.simpleMoney.Repositories.*;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CostPlanService costPlanService;
    @Autowired
    private CostPlanRepository costPlanRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpenseForUser(String month, int year, int userId) {
        return expenseRepository.findByMonthAndYearAndPerson_PersonId(month, year, userId);
    }

    @Transactional
    public void saveExpense(Expense expense, int personId, int categoryId, int accountId) {
        Person person = personService.getPerson(personId);
        Category category = categoryService.getCategoryById(categoryId);
        Account account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        // уменьшаем сумму счёт
        account.setSumma(account.getSumma() - expense.getSumma());
        // Обновляем план
        CostPlan costPlan = costPlanService.getCostPlansForUser(expense.getMonth(), expense.getYear(),
                personId).get(0);
        costPlan.setFact(costPlan.getFact() + expense.getSumma());
        // Устанавливаем недобор и резерв
        if(costPlan.getFact() - costPlan.getPlan() >= 0){
            costPlan.setOverpayment(costPlan.getFact() - costPlan.getPlan());
            costPlan.setReserve(0);
        } else{
            costPlan.setReserve(costPlan.getPlan() - costPlan.getFact());
            costPlan.setOverpayment(0);
        }
        costPlanRepository.save(costPlan);
        expense.setPerson(person);
        expense.setCategory(category);
        expense.setAccount(account);
        expenseRepository.save(expense);
        accountRepository.save(account);
        categoryRepository.save(category);
        costPlanRepository.save(costPlan);
    }
}
