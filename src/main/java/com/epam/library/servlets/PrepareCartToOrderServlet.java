package com.epam.library.servlets;

import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.*;
import com.epam.library.repositories.*;
import com.epam.library.service.OrderBookService;
import com.epam.library.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "prepareCartToOrderServlet")
public class PrepareCartToOrderServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(PrepareCartToOrderServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters of customer from session");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Integer customerId = null;
        if (customer == null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<p> You have to sign in or sign up to create an order </p>");
            out.println("</head>");
            out.println("</html>");
        } else customerId = customer.getId();

        String placeOfReading = request.getParameter("placeTitle");

        try {
            List<PlaceOfReading> allPlacesForReading = JDBCPlaceOfReading.getInstance().findAll();
            int placeOfReadingId = 0;
            for (PlaceOfReading place : allPlacesForReading) {
                if (place.getPlaceTitle().equalsIgnoreCase(placeOfReading)) {
                    placeOfReadingId = place.getId();
                }
            }

            Integer cartIdByCustomerId = JDBCCartRepository.getInstance().getCartIdByCustomerId(customerId);
            int totalQuantity = JDBCCartBookRepository.getInstance().findAllWithTitleWithJoinByCartId(cartIdByCustomerId).size();

            Date creationDate = new Date(System.currentTimeMillis());
            Date expirationDate;
            if (placeOfReadingId == 1) {
                expirationDate = new Date(System.currentTimeMillis() + 2592000000l);
            } else {
                expirationDate = creationDate;
            }

            OrderService.getInstance().addOrder(totalQuantity, customerId, creationDate, expirationDate, placeOfReadingId, cartIdByCustomerId, Boolean.TRUE);

            int orderId = JDBCOrderRepository.getInstance().findLastIdOfOrder();
            List<CartBook> booksInCart = JDBCCartBookRepository.getInstance().findAllWithTitleWithJoinByCartId(cartIdByCustomerId);
            List<Book> allBooks = JDBCBookRepository.getInstance().findAll();

            for (CartBook cartBook : booksInCart) {
                for (Book allBook : allBooks) {
                    if (cartBook.getBookTitle().equalsIgnoreCase(allBook.getTitle())){
                        cartBook.setBookId(allBook.getId());
                    }
                }
            }

            for (CartBook cartBook : booksInCart) {
                OrderBookService.getInstance().addOrderBook(cartBook.getBookId(), 1, orderId);
                Catalog bookInCatalog = JDBCCatalogRepository.getInstance().getByBookId(cartBook.getBookId());
                int bookCurrentQuantity = bookInCatalog.getFreeQuantity();
                JDBCCatalogRepository.getInstance().decreaseFreeQuantityOfBook(cartBook.getBookId(), bookCurrentQuantity);
            }

            JDBCCartRepository.getInstance().removeCartById(cartIdByCustomerId);

            List<OrderBook> booksInOrder = JDBCOrderBookRepository.getInstance().findAllWithTitleWithJoinByCartId(orderId);
            request.setAttribute("booksInOrder", booksInOrder);
            request.setAttribute("totalQuantity", totalQuantity);
            Customer customerById = JDBCCustomerRepository.getInstance().getById(customerId);
            String name = customerById.getName();
            String surname = customerById.getSurname();
            request.setAttribute("customerName", name);
            request.setAttribute("customerSurname", surname);
            request.setAttribute("creationDate", creationDate);
            request.setAttribute("expirationDate", expirationDate);
            request.setAttribute("placeOfReading", placeOfReading);

            request.getRequestDispatcher("/order.jsp").forward(request, response);

        } catch (Exception e) {
            Log.error("Error during creation order");
            throw new EntitySavingException(e);
        }
    }
}
