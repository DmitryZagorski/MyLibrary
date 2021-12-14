package com.epam.library.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private String author;
    private Date issueDate;
    private String genre;

    public Book() {
    }

    public Book(String title, String author, Date issueDate, String genre) {
        this.title = title;
        this.author = author;
        this.issueDate = issueDate;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(issueDate, book.issueDate) &&
                Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", issueDate=" + issueDate +
                ", genre='" + genre + '\'' +
                '}';
    }
}
