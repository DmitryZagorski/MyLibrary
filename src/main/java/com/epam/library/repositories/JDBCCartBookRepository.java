package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.CartBook;
import com.epam.library.repositories.mapping.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCartBookRepository extends AbstractCRUDRepository<CartBook> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCartBookRepository.class);

    private static JDBCCartBookRepository instance;

    public JDBCCartBookRepository() {
        super(new CartMapper(), "cart_books");
        instance = this;
    }

    public static synchronized JDBCCartBookRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCartBookRepository();
        }
        return instance;
    }

    @Override
    public CartBook getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<CartBook> findAll() {
        return super.findAll();
    }

    @Override
    public List<CartBook> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public CartBook addBookToCartBook(CartBook cartBook) {
        Log.info("Adding book ot cart started");
        String insertBookSQL = "insert into cart_books (book_id, quantity, cart_id) values (?,?,?)";
        String updateBookSQL = "update cart_books set book_id = ?, quantity = ?, cart_id = ? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (cartBook.getId() == 0) {
                prStatement = connection.prepareStatement(insertBookSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBookSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setCartBookValues(cartBook, prStatement);
            if (cartBook.getId() != 0) {
                prStatement.setInt(4, cartBook.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved to cart!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                cartBook.setId(generatedKey.getInt(1));
            }
            return cartBook;
        } catch (SQLException e) {
            Log.error("Something wrong during saving book to cart", e);
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

    public List<CartBook> findAllWithJoinByCartId(int cartId) {
        Log.info("Getting books in cart by Id started");
        String findAllWithJoin = "select cart_books.id, cart_books.book_id from cart_books inner join cart on cart_books.cart_id = ".concat(String.valueOf(cartId));
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllWithJoin)) {
            List<CartBook> books = new ArrayList<>();
            while (resultSet.next()) {
                CartBook cartBook = new CartBook();
                cartBook.setId(resultSet.getInt("id"));
                cartBook.setBookId(resultSet.getInt("book_id"));
                books.add(cartBook);
            }
            return books;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRerievalException(e);
        }
    }

    public List<CartBook> findAllWithTitleWithJoinByCartId(int cartId) {
        Log.info("Getting books in cart by cart_id started");
        String findAllWithJoin = "select cart_books.id, books.title from cart_books inner join books on cart_books.book_id = books.id where cart_id = ".concat(String.valueOf(cartId));
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllWithJoin)) {
            List<CartBook> books = new ArrayList<>();
            while (resultSet.next()) {
                CartBook cartBook = new CartBook();
                cartBook.setId(resultSet.getInt("id"));
                cartBook.setBookTitle(resultSet.getString("title"));
                books.add(cartBook);
            }
            return books;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRerievalException(e);
        }
    }

    private void setCartBookValues(CartBook cartBook, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting book in cart started");
        prStatement.setInt(1, cartBook.getBookId());
        prStatement.setInt(2, cartBook.getQuantity());
        prStatement.setInt(3, cartBook.getCartId());
    }
}