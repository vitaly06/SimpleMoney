package ru.oksei.talisman.simpleMoney.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expenseid")
    private int expenseid;
    @Column(name = "month")
    private String month;
    @Column(name = "year")
    private int year;
    @Column(name = "date")
    private Date date;
    @Column(name = "summa")
    private int summa;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnore
    private Category category;
    @ManyToOne
    @JoinColumn(name = "accountid")
    @JsonIgnore
    private Account account;
    @ManyToOne
    @JoinColumn(name = "personid")
    @JsonIgnore
    private Person person;


    public Expense() {}

    public Expense(String month, int year, Date date, int summa) {
        this.month = month;
        this.year = year;
        this.date = date;
        this.summa = summa;
    }


    public int getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(int expenseid) {
        this.expenseid = expenseid;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
