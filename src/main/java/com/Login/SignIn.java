package com.Login;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class SignIn extends HttpServlet {
//    private static final long serialVersionUID = 1L;

    public SignIn() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Payload = request.getParameter("payload");
        LoginService.SignIn(Payload);
//        response.setContentType("text/plain");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.print("Hello "+ Payload + "!!");
        response.sendError(404, "Not Found");
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        String Payload =request.getParameter("payload").trim();
//        doGet(request, response);
//    }
}
