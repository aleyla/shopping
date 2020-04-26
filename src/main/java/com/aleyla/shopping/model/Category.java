package com.aleyla.shopping.model;


import java.util.Objects;

public class Category {

    private Category parent;

    private String title;


    public Category(String title) {
        this.title = title;
    }

    public Category(Category parent, String title) {
        this.parent = parent;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(parent, category.parent) &&
                Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, title);
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
