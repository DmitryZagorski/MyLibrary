package com.epam.library.repositories;

import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCCustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Collection;

class JDBCCustomerRepositoryTest {

    @Test
    void should_add_new_customer() {
        //given
        JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setBirth(Date.valueOf("1990-07-21"));
        c1.setAddress("Grodno");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));

        Customer c2 = new Customer();
        c2.setName("Anton");
        c2.setSurname("Varenikow");
        c2.setBirth(Date.valueOf("1992-12-10"));
        c2.setAddress("Minsk");
        c2.setDateOfSignUp(Date.valueOf("2015-07-05"));
        //when
        instance.removeAllCustomers();
        instance.addCustomer(c1);
        instance.addCustomer(c2);
        Collection<Customer> allCustomers = instance.getAllCustomers();
        //then
        Assertions.assertEquals(2, allCustomers.size());
    }

    @Test
    void removeCustomerById() {
        //given
        JDBCCustomerRepository jdbcCR = new JDBCCustomerRepository();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setBirth(Date.valueOf("1990-07-21"));
        c1.setAddress("Grodno");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));

        //when
        jdbcCR.removeAllCustomers();
        jdbcCR.addCustomer(c1);
        int customerId = jdbcCR.getIdOfOnceCustomerInTableForTest();
        jdbcCR.removeCustomerById(customerId);
        Collection<Customer> allCustomers = jdbcCR.getAllCustomers();
        //then
        Assertions.assertEquals(0, allCustomers.size());
    }

    @Test
    void getCustomerById() {

        JDBCCustomerRepository jdbcCR = new JDBCCustomerRepository();

        jdbcCR.removeAllCustomers();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setBirth(Date.valueOf("1990-07-21"));
        c1.setAddress("Grodno");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));

        jdbcCR.addCustomer(c1);

        int customerId = jdbcCR.getIdOfOnceCustomerInTableForTest();

        jdbcCR.removeAllCustomers();

        Collection<Customer> customers = jdbcCR.getAllCustomers();

        customers.add(jdbcCR.getCustomerById(customerId));

        Assertions.assertEquals(1, customers.size());

    }

    @Test
    void getAllCustomers() {

        JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();

        Customer c1 = new Customer();
        c1.setName("Dmitry");
        c1.setSurname("Zagorski");
        c1.setBirth(Date.valueOf("1990-07-21"));
        c1.setAddress("Grodno");
        c1.setDateOfSignUp(Date.valueOf("2011-10-20"));

        Customer c2 = new Customer();
        c2.setName("Anton");
        c2.setSurname("Varenikow");
        c2.setBirth(Date.valueOf("1992-12-10"));
        c2.setAddress("Minsk");
        c2.setDateOfSignUp(Date.valueOf("2015-07-05"));

        instance.removeAllCustomers();

        instance.addCustomer(c1);
        instance.addCustomer(c2);

        Collection<Customer> allCustomers = instance.getAllCustomers();

        Assertions.assertEquals(2, allCustomers.size());

    }
}