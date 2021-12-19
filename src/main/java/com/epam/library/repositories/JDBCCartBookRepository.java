package com.epam.library.repositories;

import com.epam.library.models.CartBook;
import com.epam.library.repositories.mapping.CartBookMapper;
import com.epam.library.repositories.mapping.CartMapper;
import com.epam.library.repositories.mapping.MapperToObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JDBCCartBookRepository extends AbstractCRUDRepository<CartBook> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCartBookRepository.class);

    private static JDBCCartBookRepository instance;

    public JDBCCartBookRepository() {
        super(new CartMapper(), "cart_book");
        instance = this;
    }

    public static synchronized JDBCCartBookRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCartBookRepository();
        }
        return instance;
    }

    @Override
    public CartBook getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<CartBook> findAll() {
        return super.findAll();
    }

    @Override
    public List<CartBook> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
