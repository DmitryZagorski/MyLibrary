package com.epam.library.servletsTwo;

import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "allCustomersServlet")
public class AllCustomersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCCustomerRepository repository = JDBCCustomerRepository.getInstance();
        List<Customer> all = repository.findAll();
        request.setAttribute("allCustomers", all);
        request.getRequestDispatcher("/viewAllCustomers.jsp").forward(request, response);

    }
}
