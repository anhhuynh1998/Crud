package com.example.demo_product.dto;
public class Pageable {
    private String search;
    private int page;
    private int totalItem;
    private int totalPage;
    private String nameField;
    private String sortBy;

    public Pageable() {
    }

    public Pageable(String search, int page, int totalItem) {
        this.search = search;
        this.page = page;
        this.totalItem = totalItem;
    }

    public Pageable(String search, int page, int totalItem, String nameField, String sortBy) {
        this.search = search;
        this.page = page;
        this.totalItem = totalItem;
        this.nameField = nameField;
        this.sortBy = sortBy;
    }

    public Pageable(String search, int page, int totalItem, int totalPage) {
        this.search = search;
        this.page = page;
        this.totalItem = totalItem;
        this.totalPage = totalPage;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}