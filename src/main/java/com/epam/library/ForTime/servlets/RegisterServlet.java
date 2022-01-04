package com.epam.library.ForTime.servlets;

import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("fname");
        String surname = request.getParameter("lname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        Date dateOfSignUp = Date.valueOf(request.getParameter("dateOfSignUp"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        try {
            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();

            Customer customer = new Customer();
            customer.setName(name);
            customer.setSurname(surname);
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setDateOfSignUp(dateOfSignUp);
            customer.setLogin(login);
            customer.setPassword(password);
            customer.setRole(PersonRole.Customer);
            customer.setLocked(Boolean.FALSE);


            Customer savedCustomer = jdbcCR.saveCustomer(customer);
            request.setAttribute("addedId", savedCustomer);

            request.getRequestDispatcher("second").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
