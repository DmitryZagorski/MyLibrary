package com.epam.library.repositories.mapping;

import com.epam.library.models.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements MapperToObject<Cart> {
    @Override
    public Cart toObject(ResultSet resultSet) throws SQLException {

        Cart cart = new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setBookId(resultSet.getInt("book_id"));
        cart.setBookQuantity(resultSet.getInt("book_quantity"));
        cart.setCustomerId(resultSet.getInt("customer_id"));
        cart.setOrderId(resultSet.getInt("order_id"));
        return cart;
    }
}
