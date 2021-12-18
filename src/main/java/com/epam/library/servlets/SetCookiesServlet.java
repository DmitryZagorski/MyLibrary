package com.epam.library.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "setCookies")
public class SetCookiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("some_id", "123");
        Cookie cookie1 = new Cookie("some_name", "Dima");

        cookie.setMaxAge(60*60*24);
        cookie1.setMaxAge(60*60*24);

        response.addCookie(cookie1);
        response.addCookie(cookie);

    }
}
