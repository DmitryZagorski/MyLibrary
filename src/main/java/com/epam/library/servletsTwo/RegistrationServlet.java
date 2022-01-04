package com.epam.library.servletsTwo;

import com.epam.library.models.Customer;
import com.epam.library.models.PersonRole;
import com.epam.library.repositories.JDBCCustomerRepository;
import com.epam.library.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "registrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        Date dateOfSignUp = Date.valueOf(request.getParameter("dateOfSignUp"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        try {
            CustomerService instance = CustomerService.getInstance();
            Customer registeredCustomer = instance.registerCustomer(name, surname, address, email, dateOfSignUp, PersonRole.Customer, Boolean.FALSE, login, password);

            HttpSession session = request.getSession();
            session.setAttribute("customer", registeredCustomer);

            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
