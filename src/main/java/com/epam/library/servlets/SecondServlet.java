package com.epam.library.servlets;

import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "second")
public class SecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        try {
//            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();
//            Collection<Customer> customers = jdbcCR.getAllCustomers();
//            request.setAttribute("customers", customers);
//
//            request.getRequestDispatcher("customers").forward(request, response);
//
//        } catch (Exception e) {
//            throw new IOException(e);
//        }

    }
}
