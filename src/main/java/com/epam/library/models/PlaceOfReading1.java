package com.epam.library.models;

import java.util.Arrays;

public enum PlaceOfReading1 {
    Home,
    ReadingRoom;

    public static PlaceOfReading1 getPlaceOfReadingByOrdinal(int ordinal){
        return Arrays.asList(PlaceOfReading1.values()).stream().filter(place -> ordinal == place.ordinal()).findFirst().get();
    }

}
