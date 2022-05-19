package com.epam.library.service;

import com.epam.library.models.Cart;
import com.epam.library.models.Genre;
import com.epam.library.repositories.JDBCCartRepository;
import com.epam.library.repositories.JDBCGenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenreService {

    private static final Logger Log = LoggerFactory.getLogger(GenreService.class);

    private static GenreService instance;

    private GenreService() {
        instance = this;
    }

    public static GenreService getInstance() {
        if (instance == null) {
            instance = new GenreService();
        }
        return instance;
    }

    public Integer getGenreIdByGenreTitle(String title){
        Log.info("Getting genre Id by title");
        Genre genre = JDBCGenreRepository.getInstance().getGenreByTitle(title);
        return genre.getId();
    }

    public Cart addCart(int customerId) {
        Log.info("Setting of cart values");
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        return JDBCCartRepository.getInstance().addCart(cart);
    }

}
