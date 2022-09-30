package com.customer;

import com.Constant;
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

@WebServlet("/cart/order")
public class CartOrder extends HttpServlet {

    private String getTableName(HttpServletRequest req){
        HttpSession session = req.getSession();
        String userid = String.valueOf(session.getAttribute(Constant.Usersdata.userid));
        return "orderhistory" + userid;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tablename = getTableName(request);
        JSONArray jsoncart = null;
        String stage = Constant.Stage.Ordered;
        try {
            jsoncart = new CustomerService().findcard(Constant.DataBase_UserTableName.DBProductdata, tablename, stage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.print(jsoncart);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tablename = getTableName(request);
        new CustomerService().updatecart(tablename, "ordered");
    }
}
