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
import java.sql.SQLException;

@WebServlet("/cart/order")
public class CartOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userid = String.valueOf(session.getAttribute(Constant.Usersdata.userid));
        String tablename = "orderhistory"+userid;

        new CustomerService().updatecart(tablename, "ordered");
    }
}
