package com.epam.library.servlets;

import com.epam.library.exceptions.CatalogException;
import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "catalogServlet")
public class CatalogServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(CatalogServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Catalog> allWithJoin = JDBCCatalogRepository.getInstance().findAllWithJoinWithBookTitle();
            request.setAttribute("allCatalog", allWithJoin);

            List<Book> allBooks = JDBCBookRepository.getInstance().findAll();
            request.setAttribute("allBooks", allBooks);

            request.getRequestDispatcher("/catalog.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during forming catalog");
            throw new CatalogException(e);
        }
    }
}
