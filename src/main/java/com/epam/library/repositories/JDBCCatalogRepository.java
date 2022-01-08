package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.mapping.CatalogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCatalogRepository extends AbstractCRUDRepository<Catalog> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCatalogRepository.class);

    private static JDBCCatalogRepository instance;
    private static final String findFreeBooksSQL = "select * from catalog where free_quantity not null";

    public JDBCCatalogRepository() {
        super(new CatalogMapper(), "catalog");
    }

    public static synchronized JDBCCatalogRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCatalogRepository();
        }
        return instance;
    }

    @Override
    public Catalog getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Catalog> findAll() {
        return super.findAll();
    }

    @Override
    public List<Catalog> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public List<Catalog> getFreeBooksInLibrary() {
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findFreeBooksSQL)) {
            List<Catalog> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new CatalogMapper().toObject(resultSet));
            }
            return books;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval id ", e);
            throw new EntityRerievalException();
        }
    }

    public List<Catalog> findAllWithJoin() {
        String findAllWithJoin = "select catalog.id, books.title, catalog.total_quantity, catalog.free_quantity from catalog inner join books on catalog.book_id = books.id";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllWithJoin)) {
            List<Catalog> catalog = new ArrayList<>();
            while (resultSet.next()) {
                Catalog cat = new Catalog();
                cat.setId(resultSet.getInt("id"));
                cat.setBookTitle(resultSet.getString("title"));
                cat.setTotalQuantity(resultSet.getInt("total_quantity"));
                cat.setFreeQuantity(resultSet.getInt("free_quantity"));
                catalog.add(cat);
            }
            return catalog;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRerievalException(e);
        }
    }

    public Catalog addBookToCatalog(Catalog catalog) {
        String insertBookSQL = "insert into catalog (book_id, total_quantity, free_quantity) values (?,?,?)";
        String updateBookSQL = "update catalog set book_id = ?, total_quantity = ?, free_quantity = ? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (catalog.getId() == 0) {
                prStatement = connection.prepareStatement(insertBookSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBookSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setCatalogValues(catalog, prStatement);
            if (catalog.getId() != 0) {
                prStatement.setInt(4, catalog.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved to catalog!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                catalog.setId(generatedKey.getInt(1));
            }
            return catalog;
        } catch (SQLException e) {
            Log.error("Something wrong during saving book to catalog", e);
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

    private void setCatalogValues(Catalog catalog, PreparedStatement prStatement) throws SQLException {
        prStatement.setInt(1, catalog.getBookId());
        prStatement.setInt(2, catalog.getTotalQuantity());
        prStatement.setInt(3, catalog.getFreeQuantity());
    }
}













 /*

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


*/