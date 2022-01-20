package com.epam.library.models;

import java.sql.Date;
import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private String author;
    private Date issueDate;
    private Integer genreId;
    private String genre;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getIssueDate() {
        return (Date) issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getGenre() {
        return genre;
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
                Objects.equals(genreId, book.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, issueDate, genreId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", issueDate=" + issueDate +
                ", genreId=" + genreId +
                '}';
    }
}
