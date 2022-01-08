package com.epam.library.servletsTwo;

import com.epam.library.models.Cart;
import com.epam.library.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addBookToCartServlet")
public class AddBookToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookID = request.getParameter("bookId");
        Integer bookId = Integer.valueOf(bookID);
        String totalQuan = request.getParameter("totalQuantity");
        Integer totalQuantity = Integer.valueOf(totalQuan);
        String freeQuan = request.getParameter("freeQuantity");
        Integer freeQuantity = Integer.valueOf(freeQuan);
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Integer customerId = customer.getId();

        try{
            Cart cart = new Cart();
            cart.set
        }


    }
}
