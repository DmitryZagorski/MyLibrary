package com.epam.library.repositories.mapping;

import com.epam.library.models.BookPicture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPictureMapper implements MapperToObject<BookPicture> {

    private static final Logger Log = LoggerFactory.getLogger(BookPictureMapper.class);

    @Override
    public BookPicture toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of book picture started");
        BookPicture bookPicture = new BookPicture();
        bookPicture.setId(resultSet.getInt("id"));
        bookPicture.setName(resultSet.getString("name"));
        bookPicture.setPath(resultSet.getString("path"));
        bookPicture.setBookId(resultSet.getInt("book_id"));
        return bookPicture;
    }
}
