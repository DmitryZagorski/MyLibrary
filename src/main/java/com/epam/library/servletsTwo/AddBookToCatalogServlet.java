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
        int bookId = Integer.parseInt(bookID);
        String totalQuantity = request.getParameter("quantity");
        int totalQuantityInt = Integer.parseInt(totalQuantity);
        String freeQuantity = request.getParameter("quantity");
        int freeQuantityInt = Integer.parseInt(freeQuantity);

        try {

            Book byId = JDBCBookRepository.getInstance().getById(bookId);
            String bookTitle = byId.getTitle();

            Catalog catalog = new Catalog();
            catalog.setBookId(bookId);
            catalog.setTotalQuantity(totalQuantityInt);
            catalog.setFreeQuantity(freeQuantityInt);

            List<Catalog> allBooksInCatalog = JDBCCatalogRepository.getInstance().findAll();

            boolean needToUpdate = false;
            for (Catalog book : allBooksInCatalog) {
                if (bookId == book.getBookId()) {
                    needToUpdate = true;
                    totalQuantityInt += book.getTotalQuantity();
                }
            }
            if (needToUpdate) {
                JDBCCatalogRepository.getInstance().updateTotalQuantityOfBook(bookId, totalQuantityInt);
            } else {
                JDBCCatalogRepository.getInstance().addBookToCatalog(catalog);
            }

            request.setAttribute("addedId", catalog.getId());
            request.setAttribute("addedBookTitle", bookTitle);

            request.getRequestDispatcher("/catalogServlet").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
