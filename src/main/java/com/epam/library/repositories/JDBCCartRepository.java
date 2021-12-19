package com.epam.library.repositories;

import com.epam.library.models.Cart;
import com.epam.library.repositories.AbstractCRUDRepository;
import com.epam.library.repositories.mapping.BookPictureMapper;
import com.epam.library.repositories.mapping.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JDBCCartRepository extends AbstractCRUDRepository<Cart> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCartRepository.class);

    private static JDBCCartRepository instance;

    public JDBCCartRepository() {
        super(new CartMapper(), "cart");
        instance = this;
    }

    public static synchronized JDBCCartRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCartRepository();
        }
        return instance;
    }

    @Override
    public Cart getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Cart> findAll() {
        return super.findAll();
    }

    @Override
    public List<Cart> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
