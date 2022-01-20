package com.epam.library.servletsTwo;

import com.epam.library.exceptions.EntityRerievalException;
import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCatalogRepository;
import com.epam.library.repositories.JDBCCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchServlet")
public class SearchServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(SearchServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameter from index.jsp");
        String search = request.getParameter("query");

        try{
            Book bookByTitle = JDBCBookRepository.getInstance().getBookByTitle(search);
            int bookId = bookByTitle.getId();

            List<Catalog> foundBooks = JDBCCatalogRepository.getInstance().getByIdWithJoin(bookId);

            request.setAttribute("foundBooks", foundBooks);
            request.getRequestDispatcher("/foundBook.jsp").forward(request, response);
        } catch (Exception e) {
            Log.error("Error during searching entity");
            throw new EntityRerievalException(e);
        }
    }
}
