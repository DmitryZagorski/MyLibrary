package com.epam.library.servletsTwo;

import com.epam.library.repositories.JDBCCartBookRepository;
import com.epam.library.repositories.JDBCCustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveCartBookServlet")
public class RemoveCartBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cartID = request.getParameter("cartId");
        Integer cartId = Integer.valueOf(cartID);

        try {
            JDBCCartBookRepository.getInstance().removeById(cartId);

            request.setAttribute("removedId", cartId);

            request.getRequestDispatcher("cartServlet").forward(request, response);

        } catch (Exception e) {
            throw new IOException(e);
        }


    }
}
