package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.CustomerException;
import com.epam.library.exceptions.CustomerNotFoundException;
import com.epam.library.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JDBCCustomerRepository implements CustomerRepository {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCustomerRepository.class);

    private static JDBCCustomerRepository instance;

    public static synchronized JDBCCustomerRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCustomerRepository();
        }
        return instance;
    }

    @Override
    public void addCustomer(Customer customer) {
        String insertNewCustomer = "insert into customers (name, surname, birth, address, date_of_sign_up) values (?,?,?,?,?)";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(insertNewCustomer)) {
            prStatement.setString(1, customer.getName());
            prStatement.setString(2, customer.getSurname());
            prStatement.setDate(3, customer.getBirth());
            prStatement.setString(4, customer.getAddress());
            prStatement.setDate(5, customer.getDateOfSignUp());
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new CustomerException("Customer was not added!");
            }
        } catch (SQLException e) {
            Log.error("Something wrong during adding customer", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        String insertNewCustomer = "insert into customers (name, surname, birth, address, date_of_sign_up) values (?,?,?,?,?)";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(insertNewCustomer)) {
            prStatement.setString(1, customer.getName());
            prStatement.setString(2, customer.getSurname());
            prStatement.setDate(3, customer.getBirth());
            prStatement.setString(4, customer.getAddress());
            prStatement.setDate(5, customer.getDateOfSignUp());
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new CustomerException("Customer was not added!");
            }
            return customer;
        } catch (SQLException e) {
            Log.error("Something wrong during adding customer", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public void removeCustomerById(Integer id) {
        String findCustomer = "select * from customers where id = ".concat(id.toString());
        String removeCustomer = "delete from customers where id = ".concat(id.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findCustomer)) {
            if (resultSet.next()) {
                statement.execute(removeCustomer);
            } else {
                Log.info("Customer with that Id doesn't exist.");
            }
        } catch (SQLException e) {
            Log.error("Something wrong during removing customer by id " + id, e);
            throw new CustomerException(e);
        }
    }

    @Override
    public void removeCustomer(Integer id) {
        String removeCustomerById = "delete from customers where id="+id;
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(removeCustomerById);
        } catch (SQLException e) {
            Log.error("Something wrong during removing customer by id ", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public void removeAllCustomers() {
        String removeAllCustomers = "delete from customers";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(removeAllCustomers);
        } catch (SQLException e) {
            Log.error("Something wrong during removing all customers", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public Customer getCustomerById(Integer id) {
        String getById = "select * from customers where id = ".concat(id.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getById)) {
            if (resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                return customer;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval id " + id, e);
            throw new CustomerNotFoundException(id, e);
        }
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        String getAllCustomers = "select * from customers";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllCustomers)) {
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                customerList.add(getCustomer(resultSet));
            }
            return customerList;
        } catch (SQLException e) {
            Log.error("Something wrong during getting all customers", e);
            throw new CustomerException(e);
        }
    }

    @Override
    public Customer getCustomerByNameAndSurname(String name, String surname) {
        String getByNameAndSurname = "select * from customers where name = '" + name +
                "'  and surname = '" + surname + "'";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getByNameAndSurname)) {
            if (resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                return customer;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval name " + name + " and surname " + surname, e);
            throw new CustomerNotFoundException(name, surname, e);
        }
    }

    @Override
    public Collection<Customer> getCustomersByDateOfSignUp(Date fromDate, Date toDate) {
        String getCustomersBetweenDates = "select * from customers where date_of_sign_up between (fromDate and toDate)";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getCustomersBetweenDates)) {
            List<Customer> customersBetweenDates = new ArrayList<>();
            while (resultSet.next()) {
                customersBetweenDates.add(getCustomer(resultSet));
            }
            return customersBetweenDates;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval customers with date of sign up between " + fromDate.toString() + " and " + toDate.toString());
            throw new CustomerException(e);
        }
    }

    private Customer getCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setBirth(resultSet.getDate("birth"));
        customer.setAddress(resultSet.getString("address"));
        customer.setDateOfSignUp(resultSet.getDate("date_of_sign_up"));
        return customer;
    }

    public Integer getIdOfOnceCustomerInTableForTest() {
        String getIdOfCustomer = "select * from customers";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getIdOfCustomer)) {
            int id;
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                return id;
            }
            else return null;
        } catch (SQLException e) {
            Log.error("Error during retrieval id ", e);
            throw new CustomerException(e);
        }
    }
}
