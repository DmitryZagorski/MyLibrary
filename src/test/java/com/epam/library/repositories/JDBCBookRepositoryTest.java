package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.models.Book;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

class JDBCBookRepositoryTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db-init.sql");
        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        createStatement.executeUpdate(string);
    }

    @Test
    void getById() {

        JDBCBookRepository instance = JDBCBookRepository.getInstance();

        Book b1 = new Book();
        b1.setTitle("Bible");
        b1.setAuthor("God");
        b1.setIssueDate(Date.valueOf("1001-01-01"));
        b1.setGenre("History");

        instance.saveBook(b1);

        Book b2 = instance.getById(b1.getId());

        Assertions.assertEquals(b1, b2);

        b2.setTitle("Tom Soyer");

        instance.saveBook(b2);

        Book bookAfterUpgrade = instance.getById(b2.getId());

        Assertions.assertEquals("Tom Soyer", bookAfterUpgrade.getTitle());

    }

    @Test
    void findAll() {
    }

    @Test
    void removeById() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void saveBook() {
    }
}