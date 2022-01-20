package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Book;
import com.epam.library.models.Order;
import com.epam.library.models.PlaceOfReading;
import com.epam.library.repositories.mapping.PlaceOfReadingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCPlaceOfReading extends AbstractCRUDRepository<PlaceOfReading>{

    private static final Logger Log = LoggerFactory.getLogger(JDBCPlaceOfReading.class);

    private static JDBCPlaceOfReading instance;

    private static final String insertPlaceSQL = "insert into place_of_reading (place_title) value (?)";
    private static final String updatePlaceSQL = "update place_of_reading set place_title = ? where id = ?";

    public JDBCPlaceOfReading() {
        super(new PlaceOfReadingMapper(), "place_of_reading");
    }

    public static synchronized JDBCPlaceOfReading getInstance() {
        if (instance == null) {
            instance = new JDBCPlaceOfReading();
        }
        return instance;
    }

    @Override
    public PlaceOfReading getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<PlaceOfReading> findAll() {
        return super.findAll();
    }

    @Override
    public List<PlaceOfReading> findAllSorted(String fieldName, Integer limit, Integer offset) {
        return super.findAllSorted(fieldName, limit, offset);
    }

    @Override
    public void removeById(Integer id) {
        super.removeById(id);
    }

    @Override
    public void removeAll() {
        super.removeAll();
    }

    public PlaceOfReading addPlaceOfReading(PlaceOfReading placeOfReading) {
        Log.info("Adding place of reading");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (placeOfReading.getId() == 0) {
                prStatement = connection.prepareStatement(insertPlaceSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updatePlaceSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setPlaceOfReadingValues(placeOfReading, prStatement);
            if (placeOfReading.getId() != 0) {
                prStatement.setInt(5, placeOfReading.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Book was not saved!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                placeOfReading.setId(generatedKey.getInt(1));
            }
            return placeOfReading;
        } catch (SQLException e) {
            Log.error("Something wrong during saving book", e);
            throw new EntitySavingException(e);
        } finally {
            if (prStatement != null) {
                try {
                    prStatement.close();
                } catch (SQLException e) {
                    throw new EntitySavingException(e);
                }
            }
        }
    }

    private void setPlaceOfReadingValues(PlaceOfReading placeOfReading, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting values of reading place");
        prStatement.setString(1, placeOfReading.getPlaceTitle());
    }
}
