package com.epam.library.repositories.mapping;
import com.epam.library.models.PlaceOfReading;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOfReadingMapper implements MapperToObject<PlaceOfReading>{

    @Override
    public PlaceOfReading toObject(ResultSet resultSet) throws SQLException {

        PlaceOfReading placeOfReading = new PlaceOfReading();
        placeOfReading.setId(resultSet.getInt("id"));
        placeOfReading.setPlaceTitle(resultSet.getString("place_title"));

        return placeOfReading;
    }
}