package com.epam.library.repositories.mapping;

import com.epam.library.models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements MapperToObject<Order> {
    @Override
    public Order toObject(ResultSet resultSet) throws SQLException {

        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setCartBookId(resultSet.getInt("cart_book_id"));
        order.setTotalQuantity(resultSet.getInt("total_quantity"));
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setDate(resultSet.getDate("date"));
       order.setActive(resultSet.getBoolean("active"));
        return order;
    }
}
