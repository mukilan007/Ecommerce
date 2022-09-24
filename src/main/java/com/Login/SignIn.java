package com.Login;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class SignIn extends HttpServlet {
//    private static final long serialVersionUID = 1L;
    public SignIn() {
        super();
    }

    private Map<String, String> getPayload(HttpServletRequest request){
        String payload = request.getParameter("payload");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> maps = null;
        try {
            maps = mapper.readValue(payload, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = getPayload(request);

        if(payload == null || !(LoginService.SignIn(request, payload.get("email_id"), payload.get("password"))))
            response.sendError(401, "Unauthorized");

        PrintWriter out = response.getWriter();
        out.print(true);
//        String n=(String)session.getAttribute("uname");
//        out.close();
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        String Payload =request.getParameter("payload").trim();
//        doGet(request, response);
//    }
}
