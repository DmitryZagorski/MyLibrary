package com.epam.library.servletsTwo;

import com.epam.library.exceptions.CustomerException;
import com.epam.library.repositories.JDBCCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeCustomerServlet")
public class RemoveCustomerServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveCustomerServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters from allCustomers.jsp");
        String custId = request.getParameter("customerId");
        Integer customerId = Integer.valueOf(custId);

        try {
            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();
            jdbcCR.removeById(customerId);
            request.setAttribute("removedId", customerId);

            request.getRequestDispatcher("allCustomersServlet").forward(request, response);
        } catch (Exception e) {
            Log.error("Error during removing customer");
            throw new CustomerException(e);
        }
    }
}
