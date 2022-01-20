package com.epam.library.repositories.mapping;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements MapperToObject<Book> {

    private static final Logger Log = LoggerFactory.getLogger(BookMapper.class);

    @Override
    public Book toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of book started");
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIssueDate(resultSet.getDate("issue_date"));
        book.setGenreId(resultSet.getInt("genre_id"));
        return book;
    }
}