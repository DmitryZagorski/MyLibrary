package com.epam.library.servletsTwo;

import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeCustomerServlet")
public class RemoveCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String custId = request.getParameter("customerId");
        Integer customerId = Integer.valueOf(custId);
        try {
            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();
            jdbcCR.removeById(customerId);
            request.setAttribute("removedId", customerId);

            request.getRequestDispatcher("allCustomersServlet").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
