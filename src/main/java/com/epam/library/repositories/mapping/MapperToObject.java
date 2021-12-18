package com.epam.library.repositories.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapperToObject<T> {

    T toObject(ResultSet resultSet) throws SQLException;
}
