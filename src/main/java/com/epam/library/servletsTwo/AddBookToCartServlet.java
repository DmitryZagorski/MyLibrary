package com.epam.library.servletsTwo;

import com.epam.library.exceptions.CartException;
import com.epam.library.models.Book;
import com.epam.library.models.Cart;
import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCartRepository;
import com.epam.library.repositories.JDBCCustomerRepository;
import com.epam.library.service.CartBookService;
import com.epam.library.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "addBookToCartServlet")
public class AddBookToCartServlet extends HttpServlet {

    private static final Logger Log  = LoggerFactory.getLogger(AddBookToCartServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters from catalog.jsp");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int customerId = 0;
        if (customer == null){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<p> You have to sign in or sign up to add books to cart </p>");
            out.println("</head>");
            out.println("</html>");
        }
        else{
            customerId = customer.getId();
        }
        String bookTitle = request.getParameter("bookTitle");
        Book bookByTitle = JDBCBookRepository.getInstance().getBookByTitle(bookTitle);
        int bookId = bookByTitle.getId();

        List<Book> allBooks = JDBCBookRepository.getInstance().findAll();
        for (Book book : allBooks) {
            if (book.getId()==bookId) {
                bookId = book.getId();
            }
        }
        String bookQuan = request.getParameter("freeQuantity");
        int bookQuantity = Integer.parseInt(bookQuan);

        try {
            Customer customerById = JDBCCustomerRepository.getInstance().getById(customerId);
            String customerLogin = customerById.getLogin();
            request.setAttribute("customerLogin", customerLogin);

            Cart cart = CartService.getInstance().addCart(customerId);
            int cartId = JDBCCartRepository.getInstance().getCartIdByCustomerId(customerId);

            CartBookService.getInstance().addCartBook(bookId, bookQuantity, cartId);

            request.setAttribute("addedId", cart.getId());
            request.setAttribute("addedBookTitle", bookTitle);

            request.getRequestDispatcher("/catalogServlet").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during adding book to cart");
            throw new CartException(e);
        }
    }
}
