package com.epam.library.servletsTwo;

import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.models.Genre1;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.catalog.CatalogException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addBookToCatalogServlet")
public class AddBookToCatalogServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddBookToCatalogServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookID = request.getParameter("bookId");
        Integer bookId = Integer.valueOf(bookID);
        String totalQuantity = request.getParameter("quantity");
        Integer totalQuantityInt = Integer.valueOf(totalQuantity);
        String freeQuantity = request.getParameter("quantity");
        Integer freeQuantityInt = Integer.valueOf(freeQuantity);

        try {
            Book byId = JDBCBookRepository.getInstance().getById(bookId);
            String bookTitle = byId.getTitle();
            Catalog catalog = new Catalog();

            catalog.setBookId(bookId);
            catalog.setBookTitle(bookTitle);
            catalog.setTotalQuantity(totalQuantityInt);
            catalog.setFreeQuantity(freeQuantityInt);

            JDBCCatalogRepository.getInstance().addBookToCatalog(catalog);  //added at last

            request.setAttribute("addedId", catalog.getId());
            request.setAttribute("addedBookTitle", catalog.getBookTitle());

            request.getRequestDispatcher("/catalogServlet").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
