package com.epam.library.servletsTwo;

import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import com.epam.library.repositories.JDBCCustomerRepository;
import com.epam.library.repositories.JDBCOrderRepository;
import com.epam.library.service.CartService;
import com.epam.library.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@WebServlet(name = "addCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        Date dateOfSignUp = Date.valueOf(request.getParameter("dateOfSignUp"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            CustomerService customerService = CustomerService.getInstance();

            Customer customer = customerService.registerCustomer(name, surname, address, email, dateOfSignUp, PersonRole.Customer, Boolean.FALSE, login, password);
            request.setAttribute("addedId", customer.getId());

            int customerId = JDBCCustomerRepository.getInstance().findLastIdOfCustomer();

            CartService.getInstance().addCart(customerId);

            request.getRequestDispatcher("allCustomersServlet").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
