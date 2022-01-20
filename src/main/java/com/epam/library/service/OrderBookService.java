package com.epam.library.service;

import com.epam.library.models.OrderBook;
import com.epam.library.repositories.JDBCOrderBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderBookService {

    private static final Logger Log = LoggerFactory.getLogger(OrderBookService.class);

    private static OrderBookService instance;

    private OrderBookService() {
        instance = this;
    }

    public static OrderBookService getInstance() {
        if (instance == null) {
            instance = new OrderBookService();
        }
        return instance;
    }

    public OrderBook addOrderBook(int bookId, int quantity, int orderId) {
        Log.info("Setting book values before adding to order");
        OrderBook orderBook = new OrderBook();
        orderBook.setBookId(bookId);
        orderBook.setQuantity(quantity);
        orderBook.setOrderId(orderId);
        return JDBCOrderBookRepository.getInstance().saveOrderBook(orderBook);
    }
}
