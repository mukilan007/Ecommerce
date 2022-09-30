package com.customer;

import com.Constant;
import com.base.BaseClass;
import com.db.Query;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/cart")
public class Cart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userid = String.valueOf(session.getAttribute(Constant.Usersdata.userid));
        String tablename = "orderhistory"+userid;

        JSONArray jsoncart = null;
        try {
            jsoncart = new CustomerService().findcard(Constant.DataBase_UserTableName.DBProductdata, tablename);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.print(jsoncart);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);

        new CustomerService().addcart(request,payload);
    }
}
