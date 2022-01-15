package com.epam.library.service;

import com.epam.library.models.Cart;
import com.epam.library.models.Order;
import com.epam.library.repositories.JDBCCartRepository;
import com.epam.library.repositories.JDBCOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

public class OrderService {

    private static final Logger Log  = LoggerFactory.getLogger(CartService.class);

    private static OrderService instance;


    private OrderService(){

        instance = this;
    }

    public static OrderService getInstance(){
        if (instance==null){
            instance = new OrderService();
        }
        return instance;
    }

    public Order addOrder(int totalQuantity, int customerId, Date creationDate, Date expirationDate, int placeOfReadingId, boolean active){
        Order order = new Order();
        order.setTotalQuantity(totalQuantity);
        order.setCustomerId(customerId);
        order.setCreationDate(creationDate);
        order.setExpirationDate(expirationDate);
        order.setPlaceOfReadingId(placeOfReadingId);
        order.setActive(active);
        return JDBCOrderRepository.getInstance().addOrder(order);
    }
}