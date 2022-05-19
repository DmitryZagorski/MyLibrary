package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.*;
import com.epam.library.models.Book;
import com.epam.library.models.Cart;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.AbstractCRUDRepository;
import com.epam.library.repositories.mapping.BookMapper;
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

    public Cart addCart(Cart cart) {
        Log.info("Adding cart started");
        String insertBookSQL = "insert into cart (customer_id) values (?)";
        String updateBookSQL = "update catalog set customer_id = ? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            connection.setAutoCommit(false);
            if (cart.getId() == 0) {
                prStatement = connection.prepareStatement(insertBookSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBookSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setCartValues(cart, prStatement);
            if (cart.getId() != 0) {
                prStatement.setInt(1, cart.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Cart was not saved!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                cart.setId(generatedKey.getInt(1));
            }
            connection.commit();
            return cart;

        } catch (SQLException e) {
            Log.error("Something wrong during adding cart", e);
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

    public Integer getCartIdByCustomerId(int customerId) {
        Log.info("Getting cart by customerId started");
        String getCartByCustomerId = "select * from cart where customer_id=".concat(String.valueOf(customerId));
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getCartByCustomerId);
            Cart cart = new Cart();
            if (resultSet.next()) {
                cart.setId(resultSet.getInt("id"));
            }
            return cart.getId();
        } catch (SQLException e) {
            Log.error("Error during getting cartId by customerId=" + customerId, e);
            throw new CartException(e);
        }
    }

    private void setCartValues(Cart cart, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting cart values started");
        prStatement.setInt(1, cart.getCustomerId());
    }

    public void removeCartById(Integer id) {
        Log.info("Removing cart by cartId started");
        String removeByCartId = "delete from cart_books where cart_id = ".concat(String.valueOf(id));
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            Statement statement = connection.createStatement();
            int removing = statement.executeUpdate(removeByCartId);
            if (removing != 1) {
                Log.info("Something wrong during removing cart");
            }
        } catch (SQLException e) {
            Log.error("Something wrong during removing cart by cartId " + id, e);
            throw new EntityRemoveException(e);
        }
    }
}