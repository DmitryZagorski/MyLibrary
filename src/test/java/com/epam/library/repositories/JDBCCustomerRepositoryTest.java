package com.epam.library.repositories;

import com.epam.library.connections.ConnectionPoolProvider;
import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class JDBCCustomerRepositoryTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db-init.sql");
        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        createStatement.executeUpdate(string);
    }

    @Test
    void should_add_new_customer() {

        JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();

        Customer c1 = new Customer();
        c1.setName("First");
        c1.setSurname("Second");
        c1.setAddress("Grodno");
        c1.setEmail("1@mail.ru");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c1.setLogin("Login");
        c1.setPassword("Password");
        c1.setLocked(Boolean.FALSE);
        c1.setRole(PersonRole.Admin);

        instance.saveCustomer(c1);
        Customer c2 = instance.getById(c1.getId());
        Assertions.assertEquals(c1, c2);
        c2.setName("Boris");
        instance.saveCustomer(c2);
        Customer customerAfterUpdate = instance.getById(c2.getId());

        Assertions.assertEquals("Boris", customerAfterUpdate.getName());


    }

    @Test
    void removeCustomerById() {

        JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setAddress("Grodno");
        c1.setEmail("1@mail.ru");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c1.setLogin("Login");
        c1.setPassword("Password");
        c1.setLocked(Boolean.FALSE);
        c1.setRole(PersonRole.Admin);

        instance.removeAll();
        instance.saveCustomer(c1);
        int customerId = c1.getId();
        instance.removeById(customerId);
        List<Customer> allCustomers = instance.findAll();

        Assertions.assertEquals(0, allCustomers.size());
    }

    @Test
    void getCustomerById() {
        JDBCCustomerRepository instance = new JDBCCustomerRepository();

        instance.removeAll();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setAddress("Grodno");
        c1.setEmail("1@mail.ru");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c1.setLogin("Login");
        c1.setPassword("Password");
        c1.setLocked(Boolean.FALSE);
        c1.setRole(PersonRole.Admin);

        instance.saveCustomer(c1);

        Customer c2 = instance.getById(c1.getId());

        Assertions.assertEquals(c1, c2);
    }

    @Test
    void getCustomerByLogin() {

        JDBCCustomerRepository instance = new JDBCCustomerRepository();

        instance.removeAll();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setAddress("Grodno");
        c1.setEmail("1@mail.ru");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c1.setLogin("Log");
        c1.setPassword("Pass");
        c1.setLocked(Boolean.FALSE);
        c1.setRole(PersonRole.Admin);

        instance.saveCustomer(c1);
        Customer c2 = instance.getCustomerByLogin(c1.getLogin());

        Assertions.assertEquals(c1, c2);
    }

    @Test
    void findAllCustomers() {

        JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setAddress("Grodno");
        c1.setEmail("1@mail.ru");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c1.setLogin("Login");
        c1.setPassword("Password");
        c1.setLocked(Boolean.FALSE);
        c1.setRole(PersonRole.Admin);

        Customer c2 = new Customer();
        c2.setName("Anton");
        c2.setSurname("Kalin");
        c2.setAddress("Minsk");
        c2.setEmail("1@mail.ru");
        c2.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c2.setLogin("Login");
        c2.setPassword("Password");
        c2.setLocked(Boolean.FALSE);
        c2.setRole(PersonRole.Admin);

        instance.removeAll();
        instance.saveCustomer(c1);
        instance.saveCustomer(c2);
        List<Customer> allCustomers = instance.findAll();

        Assertions.assertEquals(2, allCustomers.size());

    }

    @Test
    void removeAllCustomers() {

        JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setAddress("Grodno");
        c1.setEmail("1@mail.ru");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c1.setLogin("Login");
        c1.setPassword("Password");
        c1.setLocked(Boolean.FALSE);
        c1.setRole(PersonRole.Admin);

        Customer c2 = new Customer();
        c2.setName("Anton");
        c2.setSurname("Kalin");
        c2.setAddress("Minsk");
        c2.setEmail("1@mail.ru");
        c2.setDateOfSignUp(Date.valueOf("2011-10-20"));
        c2.setLogin("Login");
        c2.setPassword("Password");
        c2.setLocked(Boolean.FALSE);
        c2.setRole(PersonRole.Admin);

        instance.removeAll();
        instance.saveCustomer(c1);
        instance.saveCustomer(c2);
        List<Customer> allCustomers = instance.findAll();
        int firstSize = allCustomers.size();
        instance.removeAll();
        List<Customer> allCustomers2 = instance.findAll();
        int secondSize = allCustomers2.size();
        int result = firstSize-secondSize;

        Assertions.assertEquals(2, result);
    }


}