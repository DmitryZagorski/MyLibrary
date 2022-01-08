package com.epam.library.repositories.mapping;

import com.epam.library.models.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogMapper implements MapperToObject<Catalog> {
    @Override
    public Catalog toObject(ResultSet resultSet) throws SQLException {

        Catalog catalog = new Catalog();
        catalog.setId(resultSet.getInt("id"));
        //catalog.setBookTitle(resultSet.getString("title"));
        catalog.setTotalQuantity(resultSet.getInt("total_quantity"));
        catalog.setFreeQuantity(resultSet.getInt("free_quantity"));
        return catalog;
    }
}