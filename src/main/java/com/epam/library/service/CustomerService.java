package com.epam.library.service;

import com.epam.library.exceptions.CustomerNotFoundByLogin;
import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import com.epam.library.repositories.JDBCCustomerRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.sql.Date;

public class CustomerService {

    private static final Logger Log  = LoggerFactory.getLogger(CustomerService.class);

    private static CustomerService instance;

    private CustomerService(){

        instance = this;
    }

    public static CustomerService getInstance(){
        if (instance==null){
            instance = new CustomerService();
        }
        return instance;
    }

    public Customer registerCustomer(String name, String surname, String address, String email, Date dateOfSignUp, PersonRole role, Boolean locked, String login, String password){
        Log.info("Setting customer values before registration");
        String hashPassword = hashPassword(password);
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setDateOfSignUp(dateOfSignUp);
        customer.setRole(PersonRole.Customer);
        customer.setLocked(Boolean.FALSE);
        customer.setLogin(login);
        customer.setPassword(hashPassword);

        Customer savedCustomer = JDBCCustomerRepository.getInstance().saveCustomer(customer);

        try {
            EmailService.getInstance().notifyCustomerRegistration(email, savedCustomer.getId());
        } catch (MessagingException e) {
            Log.error("Message sending failed", e);
            e.printStackTrace();
        }
        return savedCustomer;
    }

    public Customer loginCustomer(String login, String password){
        Log.info("Login of customer");
        Customer foundByLoginCustomer = JDBCCustomerRepository.getInstance().getCustomerByLogin(login);
        if (foundByLoginCustomer!=null){
            String passwordInSQL = foundByLoginCustomer.getPassword();
            String hashPassword = hashPassword(password);
            if (passwordInSQL.equals(hashPassword)){
                return foundByLoginCustomer;
            }
        }
        throw new CustomerNotFoundByLogin(login);
    }

    private String hashPassword(String password) {
        Log.info("Hashing of password");
        return DigestUtils.md5Hex(password).toUpperCase();
    }
}