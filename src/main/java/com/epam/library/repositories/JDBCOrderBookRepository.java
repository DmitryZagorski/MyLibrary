package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.OrderBook;
import com.epam.library.repositories.mapping.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderBookRepository extends AbstractCRUDRepository<OrderBook> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCOrderBookRepository.class);

    private static JDBCOrderBookRepository instance;

    public JDBCOrderBookRepository() {
        super(new CartMapper(), "order_books");
        instance = this;
    }

    public static synchronized JDBCOrderBookRepository getInstance() {
        if (instance == null) {
            instance = new JDBCOrderBookRepository();
        }
        return instance;
    }

    @Override
    public OrderBook getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<OrderBook> findAll() {
        return super.findAll();
    }

    @Override
    public List<OrderBook> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public OrderBook saveOrderBook(OrderBook orderBook) {
        Log.info("Saving book to order");
        String insertOrderSQL = "insert into order_books (book_id, quantity, order_id) values (?,?,?)";
        String updateOrderSQL = "update order_books set book_id = ?, quantity = ?, order_id = ? where id = ?";
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (orderBook.getId() == 0) {
                prStatement = connection.prepareStatement(insertOrderSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateOrderSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setOrderBookValues(orderBook, prStatement);
            if (orderBook.getId() != 0) {
                prStatement.setInt(4, orderBook.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book wasn't saved to orderBook!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                orderBook.setId(generatedKey.getInt(1));
            }
            return orderBook;
        } catch (SQLException e) {
            Log.error("Something wrong during saving book to order", e);
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

    public List<OrderBook> findAllWithTitleWithJoinByCartId(int orderId) {
        Log.info("Finding orders with titles by orderId");
        String findAllWithJoin = "select order_books.id, books.title from order_books inner join books on order_books.book_id = books.id where order_id = ".concat(String.valueOf(orderId));
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllWithJoin)) {
            List<OrderBook> books = new ArrayList<>();
            while (resultSet.next()) {
                OrderBook orderBook = new OrderBook();
                orderBook.setId(resultSet.getInt("id"));
                orderBook.setBookTitle(resultSet.getString("title"));
                books.add(orderBook);
            }
            return books;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRerievalException(e);
        }
    }

    private void setOrderBookValues(OrderBook orderBook, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting values of book in order");
        prStatement.setInt(1, orderBook.getBookId());
        prStatement.setInt(2, orderBook.getQuantity());
        prStatement.setInt(3, orderBook.getOrderId());
    }
}