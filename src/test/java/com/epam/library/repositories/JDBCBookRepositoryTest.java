package com.epam.library.repositories;

import com.epam.library.models.Book;
import com.epam.library.models.Customer;
import com.epam.library.models.Genre;
import com.epam.library.models.PersonRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class JDBCBookRepositoryTest {

    @Test
    void getById() {
        //given
        JDBCBookRepository instance = JDBCBookRepository.getInstance();

        Book b1 = new Book();
        b1.setTitle("Bible");
        b1.setAuthor("God");
        b1.setIssueDate(Date.valueOf("1001-01-01"));
        b1.setGenre(Genre.FairyTale);

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