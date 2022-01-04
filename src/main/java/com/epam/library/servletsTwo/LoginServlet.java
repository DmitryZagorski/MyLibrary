package com.epam.library.servletsTwo;

import com.epam.library.models.Customer;
import com.epam.library.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
//            JDBCCustomerRepository repo = JDBCCustomerRepository.getInstance();
//            Customer customerByLogin = repo.getCustomerByLogin(login);
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
        } catch (
                Exception e) {
            throw new IOException(e);
        }
    }
}