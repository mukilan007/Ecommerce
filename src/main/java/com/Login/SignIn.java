package com.Login;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

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
        String payload = request.getParameter("payload");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = mapper.readValue(payload, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(map == null || !(LoginService.SignIn(map.get("email_id"), map.get("password"))))
            response.sendError(401, "Unauthorized");
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        String Payload =request.getParameter("payload").trim();
//        doGet(request, response);
//    }
}
