package ru.oksei.talisman.simpleMoney.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Models.Person;

import java.util.List;

@Repository
public interface IncomePlanRepository extends JpaRepository<IncomePlan, Integer> {
    List<IncomePlan> findByMonthAndYearAndPerson_PersonId(String month, int year, int personId);
}
