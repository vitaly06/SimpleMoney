package ru.oksei.talisman.simpleMoney.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.talisman.simpleMoney.Models.Income;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findByMonthAndYearAndPerson_PersonId(String month, int year, int personId);
}
