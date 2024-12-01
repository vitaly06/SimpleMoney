package ru.oksei.talisman.simpleMoney.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.talisman.simpleMoney.Models.Expense;
import ru.oksei.talisman.simpleMoney.Models.Income;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByMonthAndYearAndPerson_PersonId(String month, int year, int personId);
}
