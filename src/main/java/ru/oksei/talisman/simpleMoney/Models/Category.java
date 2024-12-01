package ru.oksei.talisman.simpleMoney.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private int categoryId;
    @Column(name = "categorytype")
    private String categoryType;
    @Column(name = "categoryname")
    private String categoryName;
    // Пользователь
    @ManyToOne()
    @JoinColumn(name = "personid")
    @JsonIgnore
    private Person person;
    // Планы доходов
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<IncomePlan> incomePlans;
    // Планы расходов
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CostPlan> costPlans;
    // Доходы
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Income> incomes;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Expense> expenses;

    public Category(){

    }

    public Category(String categoryType, String categoryName) {
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<IncomePlan> getIncomePlans() {
        return incomePlans;
    }

    public void setIncomePlans(List<IncomePlan> incomePlans) {
        this.incomePlans = incomePlans;
    }

    public List<CostPlan> getCostPlans() {
        return costPlans;
    }

    public void setCostPlans(List<CostPlan> costPlans) {
        this.costPlans = costPlans;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
