package com.epam.library.repositories.mapping;
import com.epam.library.models.PlaceOfReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOfReadingMapper implements MapperToObject<PlaceOfReading>{

    private static final Logger Log = LoggerFactory.getLogger(PlaceOfReadingMapper.class);

    @Override
    public PlaceOfReading toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of place of reading started");
        PlaceOfReading placeOfReading = new PlaceOfReading();
        placeOfReading.setId(resultSet.getInt("id"));
        placeOfReading.setPlaceTitle(resultSet.getString("place_title"));
        return placeOfReading;
    }
}