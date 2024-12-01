package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.*;
import ru.oksei.talisman.simpleMoney.Repositories.AccountRepository;
import ru.oksei.talisman.simpleMoney.Repositories.CategoryRepository;
import ru.oksei.talisman.simpleMoney.Repositories.IncomePlanRepository;
import ru.oksei.talisman.simpleMoney.Repositories.IncomeRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IncomeService {
    private final IncomeRepository incomeRepository;
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
    private IncomePlanService incomePlanService;
    @Autowired
    private IncomePlanRepository incomePlanRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<Income> getIncomeForUser(String month, int year, int userId) {
        return incomeRepository.findByMonthAndYearAndPerson_PersonId(month, year, userId);
    }

    @Transactional
    public void saveIncome(Income income, int personId, int categoryId, int accountId) {
        Person person = personService.getPerson(personId);
        Category category = categoryService.getCategoryById(categoryId);
        Account account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        // пополняем счёт
        account.setSumma(account.getSumma() + income.getSumma());
        // Обновляем план
        IncomePlan incomePlan = incomePlanService.getIncomePlansForUser(income.getMonth(), income.getYear(),
                personId).get(0);
        incomePlan.setFact(incomePlan.getFact() + income.getSumma());
        // Устанавливаем недобор и резерв
        if(incomePlan.getFact() - incomePlan.getPlan() >= 0){
            incomePlan.setShortage(0);
            incomePlan.setReserve(incomePlan.getFact() - incomePlan.getPlan());
        } else{
            incomePlan.setReserve(0);
            incomePlan.setShortage((incomePlan.getFact() - incomePlan.getPlan()) * (-1));
        }
        incomePlanRepository.save(incomePlan);
        income.setPerson(person);
        income.setCategory(category);
        income.setAccount(account);
        incomeRepository.save(income);
        accountRepository.save(account);
        categoryRepository.save(category);
        incomePlanRepository.save(incomePlan);
    }
}
