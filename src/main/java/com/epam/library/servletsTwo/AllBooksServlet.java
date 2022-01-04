package com.epam.library.servletsTwo;

import com.epam.library.models.Book;
import com.epam.library.models.Genre1;
import com.epam.library.repositories.JDBCBookRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allBooksServlet")
public class AllBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Genre1> allGenres = JDBCBookRepository.getInstance().getAllGenres();
        request.setAttribute("allGenres", allGenres);

        JDBCBookRepository jdbcBookRepository = JDBCBookRepository.getInstance();
        List<Book> books = jdbcBookRepository.findAll();
        request.setAttribute("allBooks", books);
        request.getRequestDispatcher("/allBooks.jsp").forward(request, response);

    }
}


/*<c:forEach items="${JDBCBookRepository.getInstance().getAllGenres()}" var="genre">
    <div class="col-md-12 text-center">
        <input type = "radio" id = ${genre} name="genre"
        value="${genre}"><br>
    </div>
    </c:forEach>

    <input type="radio" name="genre" value="1">FairyTale</input><br>
    <input type="radio" name="genre" value="2">ChildrenBook</input><br>
    <input type="radio" name="genre" value="3">Historical</input><br>
    <input type="radio" name="genre" value="4">Thriller</input><br>
    <input type="submit" value="Add book">
    */