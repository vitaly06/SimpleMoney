package ru.oksei.talisman.simpleMoney.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incomeid")
    private int incomeId;
    @Column(name = "month")
    private String month;
    @Column(name = "year")
    private int year;
    @Column(name = "date")
    private String date;
    @Column(name = "summa")
    private int summa;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "personid")
    @JsonIgnore
    private Person person;


    public Income() {}

    public Income(String month, int year, String date, int summa) {
        this.month = month;
        this.year = year;
        this.date = date;
        this.summa = summa;
    }

    

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
