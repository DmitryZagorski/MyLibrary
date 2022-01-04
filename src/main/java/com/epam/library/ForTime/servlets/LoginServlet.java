package com.epam.library.ForTime.servlets;

import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();

            Customer customer = jdbcCR.getCustomerByLogin(login);

            if (customer!=null && customer.getPassword().equals(password)) {

                HttpSession session = request.getSession();
                session.setAttribute("login", login);
                session.setAttribute("name", customer.getName());
                session.setAttribute("surname", customer.getSurname());

            }
            else {
                request.setAttribute("message", "Something wrong by login");
            }

            request.getRequestDispatcher("customers").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
