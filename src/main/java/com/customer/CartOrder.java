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
    private String getUserID(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return String.valueOf(session.getAttribute(Constant.Usersdata.userid));
    }
    private String getTableName(HttpServletRequest req){
        return "orderhistory" + getUserID(req);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray jsoncart = null;
        String stage = Constant.VendorStage.ordered;
        try {
            jsoncart = new CustomerService().findcard(Constant.DataBase_UserTableName.DBProductdata,
                    Constant.DataBase_UserTableName.OrderDetail, stage);
        } catch (SQLException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        out.print(jsoncart);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cart_tablename = getTableName(request);
        String userid = getUserID(request);
        try {
            new CustomerService().updatecart(cart_tablename, Constant.DataBase_UserTableName.OrderDetail,
                    Constant.VendorStage.ordered, userid);
        } catch (SQLException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
