package com.epam.library.servletsTwo;

import com.epam.library.models.Book;
import com.epam.library.models.Cart;
import com.epam.library.models.Customer;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCartRepository;
import com.epam.library.repositories.JDBCCustomerRepository;
import com.epam.library.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addBookToCartServlet")
public class AddBookToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookTitle = request.getParameter("bookTitle");
        List<Book> allBooks = JDBCBookRepository.getInstance().findAll();
        int bookId = 0;
        for (Book book : allBooks) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                bookId = book.getId();
            }
        }
        String bookQuan = request.getParameter("freeQuantity");
        Integer bookQuantity = Integer.valueOf(bookQuan);
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Integer customerId = customer.getId();
        String ordId = request.getParameter("orderId");
        Integer orderId;
        if (ordId == null) {
            orderId = null;
        } else orderId = Integer.valueOf(ordId);

        try {
            Cart cart;
            if (orderId == null) {
                cart = CartService.getInstance().addBookToCartWithoutOrder(bookId, bookQuantity, customerId);
            } else {
                cart = CartService.getInstance().addBookToCart(bookId, bookQuantity, customerId, orderId);
            }

            Customer customerById = JDBCCustomerRepository.getInstance().getById(customerId);
            String customerLogin = customerById.getLogin();

            request.setAttribute("customerLogin", customerLogin);

            request.setAttribute("addedId", cart.getId());
            request.setAttribute("addedBookTitle", bookTitle);

            List<Cart> allCart = JDBCCartRepository.getInstance().getCartByCustomerId(customerId);
            request.setAttribute("allCart", allCart);

            request.getRequestDispatcher("/catalogServlet").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
