package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.exceptions.CustomerException;
import com.epam.library.exceptions.CustomerNotFoundException;
import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import com.epam.library.repositories.mapping.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class JDBCCustomerRepository extends AbstractCRUDRepository<Customer> {

    private static final Logger Log = LoggerFactory.getLogger(JDBCCustomerRepository.class);

    private static JDBCCustomerRepository instance;

    private static final String insertCustomerSQL = "insert into customers (name, surname, address, email, date_of_sign_up, locked, login, password, role) values (?,?,?,?,?,?,?,?,?)";
    private static final String updateCustomerSQL = "update customers set name = ?, surname = ?, address = ?, email = ?, date_of_sign_up = ?, locked = ?, login = ?, password = ?, role = ? where id = ?";
    private static final String getCustomerByNameAndSurnameSQL = "select * from customers where name = '%s' and surname = '%s'";
    private static final String getCustomerByLoginSQL = "select * from customers where login = '%s'";

    public JDBCCustomerRepository() {
        super(new CustomerMapper(), "customers");
        instance = this;
    }

    public static synchronized JDBCCustomerRepository getInstance() {
        if (instance == null) {
            instance = new JDBCCustomerRepository();
        }
        return instance;
    }

    public Customer getCustomerByNameAndSurname(String name, String surname) {
        Log.info("Getting customer by name and surname");
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(getCustomerByNameAndSurnameSQL, name, surname))) {
            if (resultSet.next()) {
                Customer customer = new CustomerMapper().toObject(resultSet);
                return customer;
            } else {
                Log.info("No customer with such name and surname");
                return null;
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval name " + name + " and surname " + surname, e);
            throw new CustomerNotFoundException(name, surname, e);
        }
    }

    public Customer getCustomerByLogin(String login) {
        Log.info("Getting customer by login");
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(getCustomerByLoginSQL, login))) {
            if (resultSet.next()) {
                Customer customer = new CustomerMapper().toObject(resultSet);
                return customer;
            } else {
                Log.info("No customer with such login");
                return null;
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval login" + login, e);
            throw new CustomerException(e);
        }
    }

    @Override
    public Customer getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Customer> findAll() {
        return super.findAll();
    }

    @Override
    public List<Customer> findAllSorted(String fieldName, Integer limit, Integer offset) {
        return super.findAllSorted(fieldName, limit, offset);
    }

    @Override
    public void removeById(Integer id) {
        super.removeById(id);
    }

    @Override
    public void removeAll() {
        super.removeAll();
    }

    public Customer saveCustomer(Customer customer) {
        Log.info("Saving customer");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            if (customer.getId() == 0) {
                prStatement = connection.prepareStatement(insertCustomerSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateCustomerSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setCustomerValues(customer, prStatement);
            if (customer.getId() != 0) {
                prStatement.setInt(10, customer.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Customer was not saved!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                customer.setId(generatedKey.getInt(1));
            }
            return customer;
        } catch (SQLException e) {
            Log.error("Something wrong during saving customer", e);
            throw new EntitySavingException(e);
        } finally {
            if (prStatement != null) {
                try {
                    prStatement.close();
                } catch (SQLException e) {
                    throw new EntitySavingException(e);
                }
            }
        }
    }

    public Integer findLastIdOfCustomer() {
        Log.info("Finding last id in table 'customers'");
        String selectLastId = "select id from cusomers ORDER BY id DESC LIMIT 1";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectLastId)) {
            Integer id = null;
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval id from customer ", e);
            throw new CustomerException(e);
        }
    }

    private void setCustomerValues(Customer customer, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting values of customer");
        prStatement.setString(1, customer.getName());
        prStatement.setString(2, customer.getSurname());
        prStatement.setString(3, customer.getAddress());
        prStatement.setString(4, customer.getEmail());
        prStatement.setDate(5, customer.getDateOfSignUp());
        prStatement.setBoolean(6, customer.getLocked());
        prStatement.setString(7, customer.getLogin());
        prStatement.setString(8, customer.getPassword());
        prStatement.setInt(9, customer.getRole().ordinal());
    }
}
