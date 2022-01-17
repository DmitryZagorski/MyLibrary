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



























/*

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

 */
}
