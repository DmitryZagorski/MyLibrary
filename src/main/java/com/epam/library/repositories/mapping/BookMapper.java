package com.epam.library.repositories.mapping;

import com.epam.library.models.Book;
import com.epam.library.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class BookMapper implements MapperToObject<Book> {
    @Override
    public Book toObject(ResultSet resultSet) throws SQLException {


        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIssueDate(resultSet.getDate("issue_date"));
//        int ordinal = resultSet.getInt("genre");
//        book.setGenreId(Genre.getGenreByOrdinal(ordinal));
        book.setGenreId(resultSet.getInt("genre_id"));

        return book;
    }
}