package com.epam.library.servletsTwo;

import com.epam.library.exceptions.BookException;
import com.epam.library.repositories.JDBCBookRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeBookServlet")
public class RemoveBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idOfBook = request.getParameter("bookId");
        Integer bookId = Integer.valueOf(idOfBook);

        try{
            JDBCBookRepository.getInstance().removeById(bookId);
            request.setAttribute("removedId", bookId);
            request.getRequestDispatcher("/allBooksServlet").forward(request, response);
        } catch (Exception e) {
            throw new BookException(e);
        }

    }
}
