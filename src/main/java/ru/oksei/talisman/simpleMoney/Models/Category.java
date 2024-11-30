package ru.oksei.talisman.simpleMoney.Models;

import jakarta.persistence.*;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personid")
    private Person person;

    public Category(){

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
}
