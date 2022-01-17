package com.epam.library.service;

import com.epam.library.models.CartBook;
import com.epam.library.repositories.JDBCCartBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartBookService {

    private static final Logger Log = LoggerFactory.getLogger(CartBookService.class);

    private static CartBookService instance;

    private CartBookService() {
        instance = this;
    }

    public static CartBookService getInstance() {
        if (instance == null) {
            instance = new CartBookService();
        }
        return instance;
    }


    public CartBook addCartBook(int bookId, int quantity, int cartId) {
        CartBook cartBook = new CartBook();
        cartBook.setBookId(bookId);
        cartBook.setQuantity(quantity);
        cartBook.setCartId(cartId);
        return JDBCCartBookRepository.getInstance().addBookToCartBook(cartBook);
    }

}
