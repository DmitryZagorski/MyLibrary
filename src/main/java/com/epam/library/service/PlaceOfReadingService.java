package com.epam.library.service;

import com.epam.library.models.PlaceOfReading;
import com.epam.library.repositories.JDBCPlaceOfReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaceOfReadingService {

    private static final Logger Log  = LoggerFactory.getLogger(PlaceOfReadingService.class);

    private static PlaceOfReadingService instance;

    private PlaceOfReadingService(){

        instance = this;
    }

    public static PlaceOfReadingService getInstance(){
        if (instance==null){
            instance = new PlaceOfReadingService();
        }
        return instance;
    }

    public PlaceOfReading addPlaceOfReading(String placeTitle){
        Log.info("Adding place of reading");
        PlaceOfReading placeOfReading = new PlaceOfReading();
        placeOfReading.setPlaceTitle(placeTitle);
        return JDBCPlaceOfReading.getInstance().addPlaceOfReading(placeOfReading);
    }
}