package com.epam.library.models;

import java.util.Objects;

public class Cart {

    private int id;
    private int CustomerId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id &&
                CustomerId == cart.CustomerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CustomerId);
    }
}
