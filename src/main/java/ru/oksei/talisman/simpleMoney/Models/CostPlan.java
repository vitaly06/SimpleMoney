package ru.oksei.talisman.simpleMoney.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "costplan")
public class CostPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid")
    private int itemId;
    @Column(name = "month")
    private String month;
    @Column(name = "year")
    private int year;
    @Column(name = "plan")
    private int plan;
    @Column(name = "fact")
    private int fact;
    @Column(name = "overpayment")
    private int overpayment;
    @Column(name = "reserve")
    private int reserve;
    // Пользователь
    @ManyToOne
    @JoinColumn(name = "personid")
    @JsonIgnore
    private Person person;
    // Категория траты
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid")
    private Category category;

    public CostPlan() {}

    public CostPlan(String month, int year, int plan, int fact, int overpayment, int reserve) {
        this.month = month;
        this.year = year;
        this.plan = plan;
        this.fact = fact;
        this.overpayment = overpayment;
        this.reserve = reserve;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public int getFact() {
        return fact;
    }

    public void setFact(int fact) {
        this.fact = fact;
    }

    public int getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(int overpayment) {
        this.overpayment = overpayment;
    }

    public int getReserve() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
