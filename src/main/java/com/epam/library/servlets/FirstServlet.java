package com.epam.library.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String test = req.getParameter("test");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<p>");
        if (test == null) {
            writer.println("No test param");
        } else {
            writer.println("Value of param: " + test);
        }
        writer.println("</  p>");

//        resp.sendRedirect("https//www.google.com");
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/newJsp");
//        dispatcher.forward(req, resp);

    }
}


/*
<%@ page import="java.util.Date,
                     com.epam.library.models.Customer,
                     com.epam.library.repositories.JDBCCustomerRepository,
                     com.epam.library.connections.ConnectionPoolProvider" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.Collection" %>
    <% String s = "The date is: ";
        Date date = new Date();%>
    <%= s + date %>

    <% JDBCCustomerRepository instance = JDBCCustomerRepository.getInstance();
        Collection<Customer> customers = instance.getAllCustomers(); %>

    <%= customers.toString() %>

 */
