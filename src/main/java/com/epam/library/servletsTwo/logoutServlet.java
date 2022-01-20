package com.epam.library.servletsTwo;

import com.epam.library.exceptions.CustomerException;
import com.epam.library.exceptions.EntitySavingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logoutServlet")
public class logoutServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(logoutServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Logout customer");

        try{
            request.getSession().setAttribute("customer", null);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during logout customer");
            throw new CustomerException(e);
        }

    }
}
