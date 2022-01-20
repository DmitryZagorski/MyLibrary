package com.epam.library.servlets;

import com.epam.library.exceptions.CustomerException;
import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allCustomersServlet")
public class AllCustomersServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AllCustomersServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            JDBCCustomerRepository repository = JDBCCustomerRepository.getInstance();
            List<Customer> all = repository.findAll();
            request.setAttribute("allCustomers", all);
            request.getRequestDispatcher("/viewAllCustomers.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.info("Error during getting all customers");
            throw new CustomerException(e);
        }
    }
}
