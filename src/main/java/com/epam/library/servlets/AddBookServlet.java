package com.epam.library.servlets;

import com.epam.library.exceptions.BookException;
import com.epam.library.models.Book;
import com.epam.library.service.BookService;
import com.epam.library.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "addBookServlet")
public class AddBookServlet extends HttpServlet {

    private static final Logger Log  = LoggerFactory.getLogger(AddBookServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters from allBooks.jsp");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        Date issueDate = Date.valueOf(request.getParameter("issueDate"));
        Integer genreId;

        try{
            genreId = GenreService.getInstance().getGenreIdByGenreTitle(genre);
            Book book = BookService.getInstance().addBook(title, author, issueDate, genreId);

            request.setAttribute("addedId", book.getId());

            request.getRequestDispatcher("allBooksServlet").forward(request, response);

        } catch (ServletException | IOException e) {
            Log.error("Error during adding book");
            throw new BookException(e);
        }
    }
}
