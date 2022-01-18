package com.epam.library.repositories.mapping;

import com.epam.library.models.CartBook;
import com.epam.library.models.OrderBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBookMapper implements MapperToObject<OrderBook> {
    @Override
    public OrderBook toObject(ResultSet resultSet) throws SQLException {
        OrderBook orderBook = new OrderBook();
        orderBook.setId(resultSet.getInt("id"));
        orderBook.setBookId(resultSet.getInt("book_id"));
        orderBook.setQuantity(resultSet.getInt("quantity"));
        orderBook.setOrderId(resultSet.getInt("order_id"));
        return orderBook;
    }
}
