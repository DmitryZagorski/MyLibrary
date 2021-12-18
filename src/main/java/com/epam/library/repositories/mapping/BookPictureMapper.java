package com.epam.library.repositories.mapping;

import com.epam.library.models.BookPicture;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPictureMapper implements MapperToObject<BookPicture> {
    @Override
    public BookPicture toObject(ResultSet resultSet) throws SQLException {

        BookPicture bookPicture = new BookPicture();
        bookPicture.setId(resultSet.getInt("id"));
        bookPicture.setName(resultSet.getString("name"));
        bookPicture.setPath(resultSet.getString("path"));
        bookPicture.setBookId(resultSet.getInt("book_id"));
        return bookPicture;
    }
}
