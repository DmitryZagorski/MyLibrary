package com.epam.library.servletsTwo;

import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.models.Genre1;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCatalogRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "prepareBooksToCatalogServlet")
public class PrepareBooksToCatalogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre1> allGenres = JDBCBookRepository.getInstance().getAllGenres();
        request.setAttribute("allGenres", allGenres);

        JDBCBookRepository jdbcBookRepository = JDBCBookRepository.getInstance();
        List<Book> books = jdbcBookRepository.findAll();
        request.setAttribute("allBooks", books);
        request.getRequestDispatcher("/addBookToCatalog.jsp").forward(request, response);
    }
}
