package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.Account;
import ru.oksei.talisman.simpleMoney.Repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

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
    public void updateAccount(Account updatedAccount, int accountId) {
        // Загрузка существующего аккаунта
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт с ID " + accountId + " не найден"));

            existingAccount.setAccountName(updatedAccount.getAccountName());
            existingAccount.setCurrency(updatedAccount.getCurrency());
            existingAccount.setSumma(updatedAccount.getSumma());

        // Сохраняем обновленный аккаунт
        accountRepository.save(existingAccount);
    }

    @Transactional
    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void transferMoney(int fromId, int toId, int amount) {
        Account accountFrom = accountRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден"));
        Account accountTo = accountRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден"));
        accountFrom.setSumma(accountFrom.getSumma() - amount);
        accountTo.setSumma(accountTo.getSumma() + amount);
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }

    public Optional<Account> getAccountById(int accountId) {
        return accountRepository.findById(accountId);
    }


}
