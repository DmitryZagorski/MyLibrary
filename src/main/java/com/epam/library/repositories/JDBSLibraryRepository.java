package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.BookException;
import com.epam.library.exceptions.CustomerException;
import com.epam.library.models.Book;
import com.epam.library.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBSLibraryRepository implements LibraryRepository {

    private static final Logger Log = LoggerFactory.getLogger(JDBSLibraryRepository.class);

    private static JDBSLibraryRepository instance;

    public static synchronized JDBSLibraryRepository getInstance() {
        if (instance == null) {
            instance = new JDBSLibraryRepository();
        }
        return instance;
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        String getAllCustomers = "select * from customers";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllCustomers)) {
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                customerList.add(getCustomer(resultSet));
            }
            return customerList;
        } catch (SQLException e) {
            Log.error("Some error during getting all customers", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public Collection<Book> getAllBooksInLibrary() {
        String getAllBooks = "select * from books";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllBooks);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting all books", e);
            throw new BookException(e);
        }
    }

    @Override
    public Collection<Book> getFreeBooksInLibrary() {
        String getFreeBooks = "select id, title, free_quantity from book_quantity where free_quantity>0 join inner books on book_id = books.id ";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getFreeBooks);
            List<Book> freeBooks = new ArrayList<>();
            while (resultSet.next()) {
                freeBooks.add(getBook(resultSet));
            }
            return freeBooks;
        } catch (SQLException e) {
            Log.error("Some error during getting free books", e);
            throw new BookException(e);
        }
    }

    private Customer getCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setBirth(resultSet.getDate("birth"));
        customer.setAddress(resultSet.getString("address"));
        return customer;
    }

    private Book getBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIssueDate(resultSet.getDate("date_of_issue"));
        book.setGenre(resultSet.getString("genre"));
        return book;
    }

}
