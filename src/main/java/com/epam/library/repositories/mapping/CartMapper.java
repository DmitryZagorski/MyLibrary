package com.epam.library.repositories.mapping;

import com.epam.library.models.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements MapperToObject<Cart> {
    @Override
    public Cart toObject(ResultSet resultSet) throws SQLException {

        Cart cart = new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setCustomerId(resultSet.getInt("customer_id"));
        return cart;
    }
}
