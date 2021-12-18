package com.epam.library.models;

import java.util.Arrays;

public enum PlaceOfReading {
    Home,
    ReadingRoom;

    public static PlaceOfReading getPlaceOfReadingByOrdinal(int ordinal){
        return Arrays.asList(PlaceOfReading.values()).stream().filter(place -> ordinal == place.ordinal()).findFirst().get();
    }

}
