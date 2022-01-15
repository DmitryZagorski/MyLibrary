package com.epam.library.models;

import java.security.PrivilegedAction;
import java.util.Objects;

public class PlaceOfReading {

    private int id;
    private String placeTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceOfReading that = (PlaceOfReading) o;
        return id == that.id &&
                Objects.equals(placeTitle, that.placeTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeTitle);
    }
}
