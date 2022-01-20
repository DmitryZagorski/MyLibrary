package com.epam.library.repositories.mapping;

import com.epam.library.models.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogMapper implements MapperToObject<Catalog> {

    private static final Logger Log = LoggerFactory.getLogger(CatalogMapper.class);

    @Override
    public Catalog toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of catalog started");
        Catalog catalog = new Catalog();
        catalog.setId(resultSet.getInt("id"));
        catalog.setBookId(resultSet.getInt("book_id"));
        catalog.setTotalQuantity(resultSet.getInt("total_quantity"));
        catalog.setFreeQuantity(resultSet.getInt("free_quantity"));
        return catalog;
    }
}