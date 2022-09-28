package com.Login;

import com.Constant;
import com.base.*;
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
    public SignIn() {super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);

        if(payload == null || !(LoginService.SignIn(request, payload)))
            response.sendError(401, "Unauthorized");
        else {
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute(Constant.Usersdata.name);
            String type = (String) session.getAttribute(Constant.Usersdata.isadmin);
            PrintWriter out = response.getWriter();
            System.out.println(name);       //TODO: Remove name
            if(type.equals("t"))
                out.printf("isadmin", name);        //TODO: need to need two parameter to js
            else
                out.printf("notadmin", name);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);

        if(!LoginService.SignUp(request, payload)) {
            response.sendError(401, "Unauthorized");
        }
        else{
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute(Constant.Usersdata.name);
            String type = (String) session.getAttribute(Constant.Usersdata.isadmin);
            PrintWriter out = response.getWriter();
            if (type.equals("t"))
                out.printf("isadmin", name);        //TODO: need to need two parameter to js
            else
                out.printf("notadmin", name);
        }
        doGet(request, response);
    }
}
