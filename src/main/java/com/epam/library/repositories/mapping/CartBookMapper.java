package com.epam.library.repositories.mapping;

import com.epam.library.models.CartBook;
import com.epam.library.models.PlaceOfReading;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartBookMapper implements MapperToObject<CartBook> {
    @Override
    public CartBook toObject(ResultSet resultSet) throws SQLException {
        CartBook cartBook = new CartBook();

        cartBook.setBookId(resultSet.getInt("id"));
        cartBook.setBookQuantity(resultSet.getInt("book_quantity"));
        cartBook.setCartId(resultSet.getInt("cart_id"));
        cartBook.setStartDate(resultSet.getDate("start_date"));
        cartBook.setExpirationDate(resultSet.getDate("expiration_date"));
        int ordinal = resultSet.getInt("place_of_reading");
        cartBook.setPlaceOfReading(PlaceOfReading.getPlaceOfReadingByOrdinal(ordinal));
        return cartBook;
    }
}