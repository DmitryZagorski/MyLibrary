package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.BookException;
import com.epam.library.exceptions.BookNotFoundException;
import com.epam.library.exceptions.CustomerException;
import com.epam.library.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JDBCBookRepository implements BookRepository {

    private static final Logger Log = LoggerFactory.getLogger(JDBCBookRepository.class);

    private static JDBCBookRepository instance;

    public static synchronized JDBCBookRepository getInstance() {
        if (instance == null) {
            instance = new JDBCBookRepository();
        }
        return instance;
    }

    @Override
    public void addBook(Book book, Integer quantity) {
        String insertNewBook = "insert into books (title, author, date_of_issue, genre) values (?,?,?,?)";
        String insertBookQuantity = "insert into book_quantity (book_id, total_quantity, free_quantity) values (?,?,?)";
        String getMaxBookId = "select max(id) from books";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(insertNewBook);
             PreparedStatement secondStatement = connection.prepareStatement(insertBookQuantity);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getMaxBookId)) {
            prStatement.setString(1, book.getTitle());
            prStatement.setString(2, book.getAuthor());
            prStatement.setDate(3, book.getIssueDate());
            prStatement.setString(4, book.getGenre());
            int result = prStatement.executeUpdate();
            if (result == 1) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    secondStatement.setInt(1, id);
                    secondStatement.setInt(2, quantity);
                    secondStatement.setInt(3, quantity);
                }
            } else {
                throw new CustomerException("Book was not added!");
            }
        } catch (SQLException e) {
            Log.error("Something wrong during adding book", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public void removeBookById(Integer id) {
        String findBookById = "select * from books where id =".concat(id.toString());
        String removeBookById = "delete from books where id=".concat(id.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findBookById)) {
            if (resultSet.next()) {
                statement.execute(removeBookById);
            } else {
                Log.info("Book with that ID doesn't exist");
            }
        } catch (SQLException e) {
            Log.error("Error during removing book by id=" + id, e);
            throw new BookException(e);
        }
    }

    @Override
    public void removeAllBooks() {
        String removeBooks = "delete from books";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(removeBooks);
        } catch (SQLException e) {
            Log.error("Some error during removing all books", e);
            throw new BookException(e);
        }
    }

    @Override
    public Book getBookById(Integer id) {
        String getBookById = "select * from books where id=".concat(id.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookById);
            if (resultSet.next()) {
                Book book = getBook(resultSet);
                return book;
            } else return null;
        } catch (SQLException e) {
            Log.error("Some error during getting book by id=" + id, e);
            throw new BookNotFoundException(id, e);
        }
    }

    @Override
    public Collection<Book> getBooksByTitle(String title) {
        String getBookByTitle = "select * from books where title=".concat(title);
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByTitle);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting book by title=" + title, e);
            throw new BookNotFoundException(title, e);
        }
    }

    @Override
    public Collection<Book> getBooksByAuthor(String author) {
        String getBookByAuthor = "select * from books where author=".concat(author);
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByAuthor);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting book by author=" + author, e);
            throw new BookNotFoundException(author, e);
        }
    }

    @Override
    public Collection<Book> getBooksByDateOfIssue(Date dateOfIssue) {
        String getBookByDate = "select * from books where date_of_issue=".concat(dateOfIssue.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByDate);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting book by title=" + dateOfIssue, e);
            throw new BookException(e);
        }
    }

    @Override
    public Collection<Book> getAllBooks() {
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

/*
String insertNewBook = "insert into books (title, author, date_of_issue, genre) values (?,?,?,?)";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(insertNewBook)) {
            prStatement.setString(1, book.getTitle());
            prStatement.setString(2, book.getAuthor());
            prStatement.setDate(3, book.getIssueDate());
            prStatement.setString(4, book.getGenre());
            int result = prStatement.executeUpdate();

            if (result != 1) {
                throw new CustomerException("Book was not added!");
            }
        } catch (SQLException e) {
            Log.error("Something wrong during adding book", e);
            throw new CustomerException(e);
        }
 */
