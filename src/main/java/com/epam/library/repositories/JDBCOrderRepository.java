package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Order;
import com.epam.library.repositories.mapping.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class JDBCOrderRepository extends AbstractCRUDRepository<Order> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCOrderRepository.class);

    private static JDBCOrderRepository instance;

    public JDBCOrderRepository() {
        super(new OrderMapper(), "orders");
    }

    public static synchronized JDBCOrderRepository getInstance() {
        if (instance == null) {
            instance = new JDBCOrderRepository();
        }
        return instance;
    }

    @Override
    public Order getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Order> findAll() {
        return super.findAll();
    }

    @Override
    public List<Order> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public Order addOrder(Order order) {
        String insertOrderSQL = "insert into orders (total_quantity, customer_id, date_of_creation, expiration_date, place_of_reading_id, active) values (?,?,?,?,?,?)";
        String updateOrderSQL = "update catalog set total_quantity = ?, customer_id = ?, date_of_creation = ?, expiration_date = ?, place_of_reading_id = ?, active = ? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (order.getId() == 0) {
                prStatement = connection.prepareStatement(insertOrderSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateOrderSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setOrderValues(order, prStatement);
            if (order.getId() != 0) {
                prStatement.setInt(7, order.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved to cart!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                order.setId(generatedKey.getInt(1));
            }
            return order;
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

    public Integer findLastIdOfOrder() {
        String selectLastId = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectLastId)) {
            Integer id = null;
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval id from order ", e);
            throw new EntityRerievalException(e);
        }
    }

    private void setOrderValues(Order order, PreparedStatement prStatement) throws SQLException {
        prStatement.setInt(1, order.getTotalQuantity());
        prStatement.setInt(2, order.getCustomerId());
        prStatement.setDate(3, order.getCreationDate());
        prStatement.setDate(4, order.getExpirationDate());
        prStatement.setInt(5, order.getPlaceOfReadingId());
        prStatement.setBoolean(6, order.isActive());
    }

}
