package com.epam.library.servletsTwo;

import com.epam.library.exceptions.EntitySavingException;
import com.epam.library.models.Cart;
import com.epam.library.models.Customer;
import com.epam.library.models.Order;
import com.epam.library.repositories.JDBCCartRepository;
import com.epam.library.repositories.JDBCOrderRepository;
import com.epam.library.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "prepareCartToOrderServlet")
public class PrepareCartToOrderServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(PrepareCartToOrderServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Customer customer = (Customer) request.getSession().getAttribute("customer");
//        Integer customerId = null;
//        if (customer == null) {
//            Log.info("To create an order you have to sign in or to sign up");
//        } else customerId = customer.getId();
//
//        String placeOfReading = request.getParameter("place");
//
//        try {
//            int placeOfReadingId = 0;
//            if (placeOfReading.equalsIgnoreCase("Home")) {
//                placeOfReadingId = 1;
//            } else if (placeOfReading.equalsIgnoreCase("ReadingRoom")) {
//                placeOfReadingId = 2;
//            }
//
//            int totalQuantity = 0;
//            JDBCCartRepository cartInstance = JDBCCartRepository.getInstance();
//            List<Cart> customerCart = cartInstance.getCartByCustomerId(customerId);
//            for (Cart cart : customerCart) {
//                totalQuantity += cart.getBookQuantity();
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            Date creationDate = calendar.getTimeInMillis();
//
//            Date expirationDate = null;
//
//            if (placeOfReading.equalsIgnoreCase("ReadingRoom")) {
//                expirationDate = creationDate;
//            } else if (placeOfReading.equalsIgnoreCase("Home")) {
//                expirationDate = creationDate;   // fix to next month !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            }
//
//            Order order = OrderService.getInstance().addOrder(totalQuantity, customerId, creationDate, expirationDate, placeOfReadingId, Boolean.FALSE);
//
//            int orderId = JDBCOrderRepository.getInstance().findLastIdOfOrder();
//
//            JDBCCartRepository.getInstance().updateCartWithOrderId(customerId, orderId);
//
//            request.setAttribute("order", order);
//
//
//        } catch (Exception e) {
//            Log.error("Something wrong during creation order");
//            throw new EntitySavingException(e);
//        }

    }
}
