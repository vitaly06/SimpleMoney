package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.Account;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Services.AccountService;
import ru.oksei.talisman.simpleMoney.Services.PersonService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PersonService personService;

    @GetMapping("/{personId}")
    public List<Account> getAccountsForPerson(@PathVariable("personId") int personId) {
        return accountService.getAccountsForPerson(personId);
    }

    @PostMapping("/addAccount")
    public void addAccount(@ModelAttribute Account account,
                           @RequestParam("personId") int personId) {
        Person person = personService.getPerson(personId);
        account.setPerson(person);
        accountService.addAccount(account);
    }

    @PostMapping("/updateAccount")
    public void updateAccount(@ModelAttribute Account account,
                              @RequestParam("accountId") int accountId) {
        accountService.updateAccount(account, accountId);
    }

    @PostMapping("/deleteAccount")
    public void deleteAccount(@RequestParam("accountId") int accountId) {
        accountService.deleteAccount(accountId);
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestParam("fromId") int fromId,
                              @RequestParam("toId") int toId,
                              @RequestParam("amount") int amount) {
        accountService.transferMoney(fromId, toId, amount);
    }
}
