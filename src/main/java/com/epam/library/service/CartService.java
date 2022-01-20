package com.epam.library.service;

import com.epam.library.models.Cart;
import com.epam.library.repositories.JDBCCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartService {

    private static final Logger Log = LoggerFactory.getLogger(CartService.class);

    private static CartService instance;

    private CartService() {
        instance = this;
    }

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public Cart addCart(int customerId) {
        Log.info("Setting of cart values");
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        return JDBCCartRepository.getInstance().addCart(cart);
    }
}
