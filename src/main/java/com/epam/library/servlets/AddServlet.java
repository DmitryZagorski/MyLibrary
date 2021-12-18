package com.epam.library.servlets;

import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("fname");
//        String surname = request.getParameter("lname");
//        Date birth = Date.valueOf(request.getParameter("birth"));
//        String address = request.getParameter("address");
//        Date dateOfSignUp = Date.valueOf(request.getParameter("dateOfSignUp"));
//        try {
//            JDBCCustomerRepository jdbcCR = JDBCCustomerRepository.getInstance();
//
//            Customer customer = new Customer();
//            customer.setName(name);
//            customer.setSurname(surname);
//            customer.setBirth(birth);
//            customer.setAddress(address);
//            customer.setDateOfSignUp(dateOfSignUp);
//
//            Customer savedCustomer = jdbcCR.saveCustomer(customer);
//            request.setAttribute("addedId", savedCustomer);
//
//            request.getRequestDispatcher("second").forward(request, response);
//
//        } catch (Exception e) {
//            throw new IOException(e);
//        }

    }
}
