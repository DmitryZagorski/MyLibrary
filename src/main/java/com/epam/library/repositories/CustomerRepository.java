package com.epam.library.repositories;

import com.epam.library.models.Customer;

public interface CustomerRepository {

    Customer getCustomerByNameAndSurname(String name, String surname);

}
