package com.epam.library.repositories.mapping;

import com.epam.library.models.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements MapperToObject<Genre> {

    private static final Logger Log = LoggerFactory.getLogger(GenreMapper.class);

    @Override
    public Genre toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of genre started");
        Genre genre = new Genre();
        genre.setTitle(resultSet.getString("title"));
        return genre;
    }
}
