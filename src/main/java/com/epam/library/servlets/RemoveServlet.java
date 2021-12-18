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

@WebServlet(name = "removeCustomer")
public class RemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  String param = request.getParameter("customerId");
        Integer customerId = Integer.valueOf(param);
        try {
            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();
            jdbcCR.removeCustomerById(customerId);
            request.setAttribute("removedId", customerId);

            request.getRequestDispatcher("second").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }
*/
    }
}
