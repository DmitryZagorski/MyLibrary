package com.epam.library.repositories.mapping;

import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements MapperToObject<Customer> {

    private static final Logger Log = LoggerFactory.getLogger(CustomerMapper.class);

    @Override
    public Customer toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of customer started");
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setAddress(resultSet.getString("address"));
        customer.setEmail(resultSet.getString("email"));
        customer.setDateOfSignUp(resultSet.getDate("date_of_sign_up"));
        customer.setLocked(resultSet.getBoolean("locked"));
        customer.setLogin(resultSet.getString("login"));
        customer.setPassword(resultSet.getString("password"));
        int ordinal = resultSet.getInt("role");
        customer.setRole(PersonRole.getRoleByOrdinal(ordinal));
        return customer;
    }
}
