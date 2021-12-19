package com.epam.library.repositories;

import com.epam.library.models.BookPicture;
import com.epam.library.repositories.mapping.BookPictureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JDBCBookPictureRepository extends AbstractCRUDRepository<BookPicture> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCBookPictureRepository.class);

    private static JDBCBookPictureRepository instance;

    public JDBCBookPictureRepository() {
        super(new BookPictureMapper(), "book_picture");
        instance = this;
    }

    public static synchronized JDBCBookPictureRepository getInstance() {
        if (instance == null) {
            instance = new JDBCBookPictureRepository();
        }
        return instance;
    }

    @Override
    public BookPicture getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<BookPicture> findAll() {
        return super.findAll();
    }

    @Override
    public List<BookPicture> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
}
