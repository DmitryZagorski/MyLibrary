package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.BookException;
import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.models.Genre;
import com.epam.library.repositories.mapping.GenreMapper;
import com.epam.library.repositories.mapping.MapperToObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCGenreRepository extends AbstractCRUDRepository<Genre> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCGenreRepository.class);

    private static JDBCGenreRepository instance;
    private static final String insertGenreSQL = "insert into genres (title) values (?)";
    private static final String updateGenreSQL = "update genres set title = ? where id = ?";
    private static final String selectIdByTitle = "select * from genres where title = '?'";

    public JDBCGenreRepository() {
        super(new GenreMapper(), "genres");
        instance = this;
    }

    public static synchronized JDBCGenreRepository getInstance() {
        if (instance == null) {
            instance = new JDBCGenreRepository();
        }
        return instance;
    }

    @Override
    public Genre getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Genre> findAll() {
        return super.findAll();
    }

    @Override
    public List<Genre> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

//    public List<Genre> getAllGenres() {
//        Log.info("Getting all genres started");
//        String findAllGenres = "select * from genres";
//        try (Connection connection = ConnectionPoolProvider.getConnection();
//             Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(findAllGenres);
//            List<Genre> genres = new ArrayList<>();
//            while (resultSet.next()) {
//                Genre genre = new Genre();
//                genre.setId(resultSet.getInt("id"));
//                genre.setTitle(resultSet.getString("title"));
//                genres.add(genre);
//            }
//            return genres;
//        } catch (SQLException e) {
//            Log.error("Retrieval of all genres failed");
//            throw new BookException(e);
//        }
//    }

    public Genre getGenreByTitle(String title){
        String a = "select * from genres where title = ".concat("'").concat(title).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(a)) {
            Genre genre = new Genre();
            if (resultSet.next()) {
                genre.setId(resultSet.getInt("id"));
                genre.setTitle(resultSet.getString("title"));
                return genre;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval id from genre", e);
            throw new EntityRerievalException();
        }
    }
}
