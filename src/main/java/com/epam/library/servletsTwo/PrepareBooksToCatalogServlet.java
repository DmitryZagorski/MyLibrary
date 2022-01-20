package com.epam.library.servletsTwo;

import com.epam.library.exceptions.CatalogException;
import com.epam.library.models.Book;
import com.epam.library.models.Genre;
import com.epam.library.repositories.JDBCBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "prepareBooksToCatalogServlet")
public class PrepareBooksToCatalogServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(PrepareBooksToCatalogServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       try{
           List<Genre> allGenres = JDBCBookRepository.getInstance().getAllGenres();
           request.setAttribute("allGenres", allGenres);

           JDBCBookRepository jdbcBookRepository = JDBCBookRepository.getInstance();
           List<Book> books = jdbcBookRepository.findAll();
           request.setAttribute("allBooks", books);
           request.getRequestDispatcher("/addBookToCatalog.jsp").forward(request, response);
       } catch (ServletException | IOException e) {
           Log.error("Error during forming list of books");
           throw new CatalogException(e);
       }
    }
}
