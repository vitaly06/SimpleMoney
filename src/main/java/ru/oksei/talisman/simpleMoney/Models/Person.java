package ru.oksei.talisman.simpleMoney.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personid")
    private int personId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    // Все категории пользователя
    @OneToMany (mappedBy = "person")
    private List<Category> categories;
    // Планы доходов
    @OneToMany(mappedBy = "person")
    private List<IncomePlan> incomePlans;
    // Планы расходов
    @OneToMany(mappedBy = "person")
    private List<CostPlan> costPlans;
    // Счета
    @OneToMany(mappedBy = "person")
    private List<Account> accounts;

    public Person() {}

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
