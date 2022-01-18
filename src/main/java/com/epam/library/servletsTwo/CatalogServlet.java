package com.epam.library.servletsTwo;

import com.epam.library.models.Book;
import com.epam.library.models.Catalog;
import com.epam.library.repositories.JDBCBookRepository;
import com.epam.library.repositories.JDBCCatalogRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "catalogServlet")
public class CatalogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //List<Catalog> allWithJoin = JDBCCatalogRepository.getInstance().findAllWithJoinWithBookId();
        List<Catalog> allWithJoin = JDBCCatalogRepository.getInstance().findAllWithJoinWithBookTitle();
        request.setAttribute("allCatalog", allWithJoin);

        List<Book> allBooks = JDBCBookRepository.getInstance().findAll();
        request.setAttribute("allBooks", allBooks);

       // request.getRequestDispatcher("/catalog.jsp").forward(request, response);
        request.getRequestDispatcher("/sectionCatalog.jsp").forward(request, response);

    }
}
