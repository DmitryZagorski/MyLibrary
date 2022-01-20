package com.epam.library.servlets;

import com.epam.library.exceptions.BookException;
import com.epam.library.repositories.JDBCBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeBookServlet")
public class RemoveBookServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveBookServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.info("Getting parameters from allBooks.jsp");
        String idOfBook = request.getParameter("bookId");
        Integer bookId = Integer.valueOf(idOfBook);

        try{
            JDBCBookRepository.getInstance().removeById(bookId);
            request.setAttribute("removedId", bookId);
            request.getRequestDispatcher("/allBooksServlet").forward(request, response);
        } catch (Exception e) {
            Log.error("Error during removing book");
            throw new BookException(e);
        }
    }
}
