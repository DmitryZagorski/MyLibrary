package com.epam.library.repositories;

import com.epam.library.models.Order;
import com.epam.library.repositories.mapping.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JDBCOrderRepository extends AbstractCRUDRepository<Order> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCOrderRepository.class);

    private static JDBCOrderRepository instance;

    public JDBCOrderRepository() {
        super(new OrderMapper(), "order");
    }

    public static synchronized JDBCOrderRepository getInstance() {
        if (instance == null) {
            instance = new JDBCOrderRepository();
        }
        return instance;
    }

    @Override
    public Order getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Order> findAll() {
        return super.findAll();
    }

    @Override
    public List<Order> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
