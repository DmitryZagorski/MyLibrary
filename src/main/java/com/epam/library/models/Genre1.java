package com.epam.library.models;

import java.util.Objects;

public class Genre1 {

    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre1 genre1 = (Genre1) o;
        return id == genre1.id &&
                Objects.equals(title, genre1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Genre1{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
