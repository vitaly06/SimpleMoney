package ru.oksei.talisman.simpleMoney.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountid")
    private int accountId;
    @Column(name = "accountname")
    private String accountName;
    @Column(name = "currency")
    private String currency;
    @Column(name = "summa")
    private int summa;
    @ManyToOne()
    @JoinColumn(name = "personid")
    @JsonIgnore
    private Person person;

    public Account() {}

    public Account(String accountName, String currency, int summa) {
        this.accountName = accountName;
        this.currency = currency;
        this.summa = summa;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
