package ru.oksei.talisman.simpleMoney.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.talisman.simpleMoney.Models.CostPlan;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;

import java.util.List;

@Repository
public interface CostPlanRepository extends JpaRepository<CostPlan, Integer> {
    List<CostPlan> findByMonthAndYearAndPerson_PersonId(String month, int year, int personId);
}
