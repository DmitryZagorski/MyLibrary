package com.epam.library.service;

import com.epam.library.models.Cart;
import com.epam.library.repositories.JDBCCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartService {

    private static final Logger Log  = LoggerFactory.getLogger(CartService.class);

    private static CartService instance;


    private CartService(){

        instance = this;
    }

    public static CartService getInstance(){
        if (instance==null){
            instance = new CartService();
        }
        return instance;
    }

    public Cart addBookToCart(int bookId, int bookQuantity, int customerId, int order_id){
        Cart cart = new Cart();
        cart.setBookId(bookId);
        cart.setBookQuantity(bookQuantity);
        cart.setCustomerId(customerId);
        cart.setOrderId(order_id);
        return JDBCCartRepository.getInstance().addBookToCart(cart);
    }

    public Cart addBookToCartWithoutOrder(int bookId, int bookQuantity, int customerId){
        Cart cart = new Cart();
        cart.setBookId(bookId);
        cart.setBookQuantity(bookQuantity);
        cart.setCustomerId(customerId);
        return JDBCCartRepository.getInstance().addBookToCartWithoutOrder(cart);
    }

}
