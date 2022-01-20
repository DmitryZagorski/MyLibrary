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
import java.util.List;

class JDBCBookRepositoryTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db-init.sql");
        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        createStatement.executeUpdate(string);
    }

    @Test
    void should_get_book_by_id() {

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
    void should_remove_book_by_id() {

        List<Book> allBooks = JDBCBookRepository.getInstance().findAll();
        int firstSize = allBooks.size();

        Book book = new Book();
        book.setId(555);
        book.setTitle("first");
        book.setAuthor("second");
        book.setGenreId(1);

        int secondSize = allBooks.size();
        Assertions.assertEquals(secondSize, firstSize + 1);
        int bookId = book.getId();
        JDBCBookRepository.getInstance().removeById(bookId);
        int thirdSize = allBooks.size();

        Assertions.assertEquals(firstSize, thirdSize);

    }

    @Test
    void should_remove_all() {

        JDBCBookRepository instance = JDBCBookRepository.getInstance();

        Book firstBook = new Book();
        firstBook.setId(555);
        firstBook.setTitle("first");
        firstBook.setAuthor("second");
        firstBook.setGenreId(1);

        Book secondBook = new Book();
        secondBook.setId(555);
        secondBook.setTitle("first");
        secondBook.setAuthor("second");
        secondBook.setGenreId(1);

        instance.saveBook(firstBook);
        instance.saveBook(secondBook);
        List<Book> allBooks = instance.findAll();
        int listSize = allBooks.size();
        instance.removeAll();

        Assertions.assertEquals(0, listSize);
    }

    @Test
    void should_find_all_books() {

        JDBCBookRepository instance = JDBCBookRepository.getInstance();

        instance.removeAll();

        Book firstBook = new Book();
        firstBook.setId(555);
        firstBook.setTitle("first");
        firstBook.setAuthor("second");
        firstBook.setGenreId(1);

        Book secondBook = new Book();
        secondBook.setId(555);
        secondBook.setTitle("first");
        secondBook.setAuthor("second");
        secondBook.setGenreId(1);

        instance.saveBook(firstBook);
        instance.saveBook(secondBook);
        List<Book> allBooks = instance.findAll();

        Assertions.assertEquals(2, allBooks.size());

    }


    @Test
    void should_add_book() {

        JDBCBookRepository instance = JDBCBookRepository.getInstance();

        Book firstBook = new Book();
        firstBook.setId(555);
        firstBook.setTitle("first");
        firstBook.setAuthor("second");
        firstBook.setGenreId(1);

        List<Book> firstCondition = instance.findAll();
        int firstSize = firstCondition.size();
        instance.saveBook(firstBook);
        List<Book> secondCondition = instance.findAll();
        int secondSize = secondCondition.size();

        Assertions.assertEquals(firstSize+1, secondSize);
    }
}