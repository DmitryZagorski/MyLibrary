package com.epam.library.repositories.mapping;

import com.epam.library.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements MapperToObject<Cart> {

    private static final Logger Log = LoggerFactory.getLogger(CartMapper.class);

    @Override
    public Cart toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of cart started");
        Cart cart = new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setCustomerId(resultSet.getInt("customer_id"));
        return cart;
    }
}
