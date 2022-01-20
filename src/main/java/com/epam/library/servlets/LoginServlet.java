package com.epam.library.servlets;

import com.epam.library.models.Customer;
import com.epam.library.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet")
public class LoginServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters from formRegister.jsp");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            CustomerService cs = CustomerService.getInstance();
            Customer customerByLogin = cs.loginCustomer(login, password);

            if (customerByLogin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customerByLogin);
            }
            else {
                request.setAttribute("message", "Something wrong by login");
            }

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            Log.error("Error during login customer");
            throw new IOException(e);
        }
    }
}