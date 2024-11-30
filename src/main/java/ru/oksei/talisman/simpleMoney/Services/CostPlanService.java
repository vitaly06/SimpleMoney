package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.CostPlan;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Repositories.CostPlanRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CostPlanService {
    private final CostPlanRepository costPlanRepository;
    @Autowired
    public CostPlanService(CostPlanRepository costPlanRepository) {
        this.costPlanRepository = costPlanRepository;
    }

    @Transactional
    public void saveCostPlan(CostPlan costPlan) {
        // Устанавливаем недобор и резерв
        if(costPlan.getFact() - costPlan.getPlan() <= 0){
            costPlan.setOverpayment(0);
            costPlan.setReserve(costPlan.getPlan() - costPlan.getFact());
        } else{
            costPlan.setReserve(0);
            costPlan.setOverpayment(costPlan.getFact() - costPlan.getPlan());
        }
        costPlanRepository.save(costPlan);
    }

    public List<CostPlan> getCostPlansForUser(String month, int year, int userId) {
        return costPlanRepository.findByMonthAndYearAndPerson_PersonId(month, year, userId);
    }
}
