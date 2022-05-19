package com.epam.library.servlets;

import com.epam.library.exceptions.CatalogException;
import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCatalogRepository;
import com.epam.library.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addBookToCatalogServlet")
public class AddBookToCatalogServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddBookToCatalogServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Log.info("Getting parameters from addBookToCatalog.jsp");
        String bookID = request.getParameter("bookId");
        int bookId = Integer.parseInt(bookID);
        String totalQuantity = request.getParameter("quantity");
        int totalQuantityInt = Integer.parseInt(totalQuantity);
        int freeQuantityInt = totalQuantityInt;

        try {
            Catalog catalog = CatalogService.getInstance().addOrUpdateBookInCatalog(bookId, totalQuantityInt, freeQuantityInt);

            request.setAttribute("addedId", catalog.getId());

            Book byId = JDBCBookRepository.getInstance().getById(bookId);
            request.setAttribute("addedBookTitle", byId.getTitle());

            request.getRequestDispatcher("/catalogServlet").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during adding book to catalog");
            throw new CatalogException(e);
        }
    }
}
