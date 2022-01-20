package com.epam.library.repositories.mapping;

import com.epam.library.models.CartBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartBookMapper implements MapperToObject<CartBook> {

    private static final Logger Log = LoggerFactory.getLogger(CartBookMapper.class);

    @Override
    public CartBook toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of cartBook started");
        CartBook cartBook = new CartBook();
        cartBook.setId(resultSet.getInt("id"));
        cartBook.setBookId(resultSet.getInt("book_id"));
        cartBook.setQuantity(resultSet.getInt("quantity"));
        cartBook.setCartId(resultSet.getInt("cart_id"));
        return cartBook;
    }
}
