package com.epam.library.repositories.mapping;

import com.epam.library.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements MapperToObject<Order> {

    private static final Logger Log = LoggerFactory.getLogger(OrderMapper.class);

    @Override
    public Order toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of order started");
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setTotalQuantity(resultSet.getInt("total_quantity"));
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setCreationDate(resultSet.getDate("date_of_creation"));
        order.setExpirationDate(resultSet.getDate("expiration_date"));
        order.setPlaceOfReadingId(resultSet.getInt("place_of_reading_id"));
        order.setCartId(resultSet.getInt("cart_id"));
        order.setActive(resultSet.getBoolean("active"));
        return order;
    }
}
