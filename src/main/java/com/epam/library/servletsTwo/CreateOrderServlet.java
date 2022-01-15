package com.epam.library.servletsTwo;

import com.epam.library.models.Cart;
import com.epam.library.models.Customer;
import com.epam.library.models.Order;
import com.epam.library.repositories.JDBCCartRepository;
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

@WebServlet(name = "createOrderServlet")
public class CreateOrderServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(CreateOrderServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String custId = request.getParameter("customerId");
//        Integer customerId = Integer.valueOf(custId);

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Integer customerId = null;
        if (customer == null){
            Log.info("To create an order you have to sign in or to sign up");
        }
        else customerId = customer.getId();

        try {
            int totalQuantity = 0;
            JDBCCartRepository cartInstance = JDBCCartRepository.getInstance();
            List<Cart> customerCart = cartInstance.getCartByCustomerId(customerId);
            for (Cart cart : customerCart) {
                totalQuantity += cart.getBookQuantity();
            }

            Calendar calendar = Calendar.getInstance();
            Date creationDate = (Date) calendar.getTime();
            Date expirationDate = (Date) calendar.getTime();


            String placeOfReading = request.getParameter("place");
            Integer placeOfReadingId = Integer.valueOf(placeOfReading);


            request.getRequestDispatcher("/order.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
