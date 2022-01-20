package com.epam.library.servlets;

import com.epam.library.exceptions.CartBookException;
import com.epam.library.repositories.JDBCCartBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeCartBookServlet")
public class RemoveCartBookServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveCartBookServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters from cart.jsp");
        String cartID = request.getParameter("cartId");
        Integer cartId = Integer.valueOf(cartID);

        try {
            JDBCCartBookRepository.getInstance().removeById(cartId);

            request.setAttribute("removedId", cartId);

            request.getRequestDispatcher("cartServlet").forward(request, response);

        } catch (Exception e) {
            Log.error("Error during removing book from cart");
            throw new CartBookException(e);
        }
    }
}
