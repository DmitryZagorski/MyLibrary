package com.epam.library.servletsTwo;

import com.epam.library.service.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "sendEmailServlet")
public class sendEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("request");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        try{
            EmailService emailService = EmailService.getInstance();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
