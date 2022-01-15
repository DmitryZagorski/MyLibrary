package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Book;
import com.epam.library.models.Cart;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.AbstractCRUDRepository;
import com.epam.library.repositories.mapping.BookPictureMapper;
import com.epam.library.repositories.mapping.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCartRepository extends AbstractCRUDRepository<Cart> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCartRepository.class);

    private static JDBCCartRepository instance;

    public JDBCCartRepository() {
        super(new CartMapper(), "cart");
        instance = this;
    }

    public static synchronized JDBCCartRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCartRepository();
        }
        return instance;
    }

    @Override
    public Cart getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Cart> findAll() {
        return super.findAll();
    }

    @Override
    public List<Cart> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public Cart addBookToCart(Cart cart) {
        String insertBookSQL = "insert into cart (book_id, book_quantity, customer_id, order_id) values (?,?,?,?)";
        String updateBookSQL = "update catalog set book_id = ?, book_quantity = ?, customer_id = ?, order_id=? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (cart.getId() == 0) {
                prStatement = connection.prepareStatement(insertBookSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBookSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setCartValues(cart, prStatement);
            if (cart.getId() != 0) {
                prStatement.setInt(5, cart.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved to cart!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                cart.setId(generatedKey.getInt(1));
            }
            return cart;
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

    public Cart addBookToCartWithoutOrder(Cart cart) {
        String insertBookSQL = "insert into cart (book_id, book_quantity, customer_id) values (?,?,?)";
        String updateBookSQL = "update catalog set book_id = ?, book_quantity = ?, customer_id = ? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (cart.getId() == 0) {
                prStatement = connection.prepareStatement(insertBookSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBookSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setCartValuesWithoutOrder(cart, prStatement);
            if (cart.getId() != 0) {
                prStatement.setInt(4, cart.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved to cart!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                cart.setId(generatedKey.getInt(1));
            }
            return cart;
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

    public void updateCartWithOrderId(int customerId, int orderId) {
        String updateCart = "update 'cart' set 'order_id' = ".concat(String.valueOf(orderId)).concat(" where customer_id = ").concat(String.valueOf(customerId));
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateCart)) {
        ps.executeUpdate();
        } catch (SQLException e) {
            Log.error("Something wrong during updating cart with orderID");
            throw new EntitySavingException(e);
        }
    }

    private void setCartValues(Cart cart, PreparedStatement prStatement) throws SQLException {
        prStatement.setInt(1, cart.getBookId());
        prStatement.setInt(2, cart.getBookQuantity());
        prStatement.setInt(3, cart.getCustomerId());
        prStatement.setInt(4, cart.getOrderId());
    }

    private void setCartValuesWithoutOrder(Cart cart, PreparedStatement prStatement) throws SQLException {
        prStatement.setInt(1, cart.getBookId());
        prStatement.setInt(2, cart.getBookQuantity());
        prStatement.setInt(3, cart.getCustomerId());
    }

    public List<Cart> getCartByCustomerId(int customerId) {
        String getByCustomerId = "select * from cart where customer_id = %d";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(getByCustomerId, customerId))) {
            List<Cart> cart = new ArrayList<>();
            while (resultSet.next()) {
                Cart someCart = new Cart();
                someCart.setBookId(resultSet.getInt("book_id"));
                someCart.setBookQuantity(resultSet.getInt("book_quantity"));
                someCart.setCustomerId(resultSet.getInt("customer_id"));
                someCart.setOrderId(resultSet.getInt("order_id"));
                cart.add(someCart);
            }
            return cart;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval cartList ", e);
            throw new EntityRerievalException(e);
        }
    }
}
