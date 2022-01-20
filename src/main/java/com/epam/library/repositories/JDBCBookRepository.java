package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.BookException;
import com.epam.library.exceptions.BookNotFoundException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Book;
import com.epam.library.models.Genre;
import com.epam.library.repositories.mapping.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCBookRepository extends AbstractCRUDRepository<Book> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCBookRepository.class);

    private static JDBCBookRepository instance;
    private static final String insertBookSQL = "insert into books (title, author, issue_date, genre_id) values (?,?,?,?)";
    private static final String updateBookSQL = "update books set title = ?, author = ?, issue_date = ?, genre_id = ? where id = ?";

    public JDBCBookRepository() {
        super(new BookMapper(), "books");
        instance = this;
    }

    public static synchronized JDBCBookRepository getInstance() {
        if (instance == null) {
            instance = new JDBCBookRepository();
        }
        return instance;
    }

    @Override
    public Book getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Book> findAll() {
        return super.findAll();
    }

    @Override
    public List<Book> findAllSorted(String fieldName, Integer limit, Integer offset) {
        return super.findAllSorted(fieldName, limit, offset);
    }

    @Override
    public void removeById(Integer id) {
        super.removeById(id);
    }

    @Override
    public void removeAll() {
        super.removeAll();
    }

    public Book saveBook(Book book) {
        Log.info("Saving book started");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (book.getId() == 0) {
                prStatement = connection.prepareStatement(insertBookSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBookSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setBookValues(book, prStatement);
            if (book.getId() != 0) {
                prStatement.setInt(5, book.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                book.setId(generatedKey.getInt(1));
            }
            return book;
        } catch (SQLException e) {
            Log.error("Something wrong during saving book", e);
            throw new EntitySavingException(e);
        } finally {
            if (prStatement != null) {
                try {
                    prStatement.close();
                } catch (SQLException e) {
                    throw new EntitySavingException(e);
                }
            }
        }
    }

    public List<Book> getBooksByTitle(String title) {
        Log.info("Getting list of books by title started");
        String getBookByTitle = "select * from books where title=".concat(title);
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByTitle);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new BookMapper().toObject(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting book by title=" + title, e);
            throw new BookNotFoundException(title, e);
        }
    }

    public Book getBookByTitle(String title) {
        Log.info("Getting book by title started");
        String getBookByTitle = "select * from books where title = ".concat("'").concat(title).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByTitle);
            Book book = new Book();
            if (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenreId(resultSet.getInt("genre_id"));
            }
            return book;
        } catch (SQLException e) {
            Log.error("Some error during getting book by title=" + title, e);
            throw new BookNotFoundException(title, e);
        }
    }

    public Book getBookByTitleWithStringGenre(String title) {
        Log.info("Getting book by title with string genre started");
        String getBookByTitle = "select books.title, books.author, books.issue_date, genres.title from books inner join genres on books.genre_id = genres.id where title = '".concat(title).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByTitle);
            Book book = new Book();
            if (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenreId(resultSet.getInt("genre_id"));
            }
            return book;
        } catch (SQLException e) {
            Log.error("Some error during getting book by title=" + title, e);
            throw new BookNotFoundException(title, e);
        }
    }

    public List<Book> getBooksByAuthor(String author) {
        Log.info("Getting book by author started");
        String getBookByAuthor = "select * from books where author=".concat(author);
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByAuthor);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new BookMapper().toObject(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting book by author=" + author, e);
            throw new BookNotFoundException(author, e);
        }
    }

    public List<Book> getBooksByDateOfIssue(Date dateOfIssue) {
        Log.info("Getting book by date of issue started");
        String getBookByDate = "select * from books where date_of_issue=".concat(dateOfIssue.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getBookByDate);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new BookMapper().toObject(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Some error during getting book by date of issue=" + dateOfIssue, e);
            throw new BookException(e);
        }
    }

    public List<Genre> getAllGenres() {
        Log.info("Getting all genres started");
        String findAllGenres = "select * from genres";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(findAllGenres);
            List<Genre> genres = new ArrayList<>();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setTitle(resultSet.getString("title"));
                genres.add(genre);
            }
            return genres;
        } catch (SQLException e) {
            Log.error("Retrieval of all genres failed");
            throw new BookException(e);
        }
    }

    private void setBookValues(Book book, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting books values started");
        prStatement.setString(1, book.getTitle());
        prStatement.setString(2, book.getAuthor());
        prStatement.setDate(3, book.getIssueDate());
        prStatement.setInt(4, book.getGenreId());
    }
}