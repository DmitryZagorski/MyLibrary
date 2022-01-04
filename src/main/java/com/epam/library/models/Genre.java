package com.epam.library.models;

import java.util.Arrays;

public enum Genre {

    FairyTale,
    ChildrenBook,
    Historical,
    Thriller;

    public static Genre getGenreByOrdinal(int ordinal){
        return Arrays.asList(Genre.values()).stream().filter(genre -> ordinal == genre.ordinal()).findFirst().get();
    }

    public static Genre getGenreByName(String name){
        Genre genreByName = null;
        Genre[] genres = Genre.values();
        for (Genre genre : genres) {
            if (genre.toString().equals(name)){
                genreByName = genre;
            }
        }
        return genreByName;
    }
}
