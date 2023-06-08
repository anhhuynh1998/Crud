package com.example.crud_book.model;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private Date date;
    private String author;
    private Category category;

    public Book() {
    }

    public Book(int id, String name, Date date, String author, Category category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.author = author;
        this.category = category;
    }

    public Book(String name, Date date, String author, Category category) {
        this.name = name;
        this.date = date;
        this.author = author;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
