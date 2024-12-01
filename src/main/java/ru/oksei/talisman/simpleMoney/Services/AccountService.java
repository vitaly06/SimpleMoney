package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.Account;
import ru.oksei.talisman.simpleMoney.Repositories.AccountRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccountsForPerson(int id){
        return accountRepository.findByPerson_PersonId(id);
    }

    @Transactional
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public void updateAccount(Account account, int accountId) {
        account.setAccountId(accountId);
        accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }
}
