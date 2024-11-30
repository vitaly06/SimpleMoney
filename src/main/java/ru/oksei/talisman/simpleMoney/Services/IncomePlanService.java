package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.IncomePlan;
import ru.oksei.talisman.simpleMoney.Repositories.IncomePlanRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IncomePlanService {
    private final IncomePlanRepository incomePlanRepository;

    @Autowired
    public IncomePlanService(IncomePlanRepository incomePlanRepository) {
        this.incomePlanRepository = incomePlanRepository;
    }

    @Transactional
    public void saveIncomePlan(IncomePlan incomePlan) {
        // Устанавливаем недобор и резерв
        if(incomePlan.getFact() - incomePlan.getPlan() >= 0){
            incomePlan.setShortage(0);
            incomePlan.setReserve(incomePlan.getFact() - incomePlan.getPlan());
        } else{
            incomePlan.setReserve(0);
            incomePlan.setShortage((incomePlan.getFact() - incomePlan.getPlan()) * (-1));
        }
        incomePlanRepository.save(incomePlan);
    }

    public List<IncomePlan> getIncomePlansForUser(String month, int year, int userId) {
        return incomePlanRepository.findByMonthAndYearAndPerson_PersonId(month, year, userId);
    }
}
