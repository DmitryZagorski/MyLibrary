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

}
