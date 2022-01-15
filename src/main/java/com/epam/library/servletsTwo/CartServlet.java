package com.epam.library.servletsTwo;

import com.epam.library.models.Cart;
import com.epam.library.models.Customer;
import com.epam.library.models.PlaceOfReading;
import com.epam.library.repositories.JDBCCartRepository;
import com.epam.library.repositories.JDBCCustomerRepository;
import com.epam.library.repositories.JDBCPlaceOfReading;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "cartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Integer customerId;
        if (customer == null){
            customerId = null;
        }
        else customerId = customer.getId();

        try {

            if (customerId!=null){
                Customer customerById = JDBCCustomerRepository.getInstance().getById(customerId);
                String customerLogin = customerById.getLogin();
                request.setAttribute("customerLogin", customerLogin);
                List<Cart> allCart = JDBCCartRepository.getInstance().findAll();
                request.setAttribute("allCart", allCart);
                List<PlaceOfReading> allPlaces = JDBCPlaceOfReading.getInstance().findAll();
                request.setAttribute("allPlaces", allPlaces);

                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
            else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<head>");
                out.println("<p> To see the cart, you have to sign in or sign up </p>");
                out.println("</head>");
                out.println("</html>");
            }


        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
