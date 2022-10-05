package com.customer;

import com.Constant;
import com.base.BaseClass;
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
    private String getUserID(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return String.valueOf(session.getAttribute(Constant.Usersdata.userid));
    }
    private String getTableName(HttpServletRequest req){
        return "orderhistory" + getUserID(req);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tablename = getTableName(request);
        String stage = Constant.CustomerStage.cart;
        JSONArray jsoncart = null;
        HttpSession session = request.getSession(false);
        String userid = getUserID(request);
        try {
            jsoncart = new CustomerService(session).findcard(Constant.DataBase_UserTableName.DBProductdata, tablename,
                    stage);
        } catch (SQLException e) {
            response.sendError(401, "SQL Error");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SessionException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.print(jsoncart);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);
        String tablename = getTableName(request);
        HttpSession session = request.getSession(false);
        try {
            new CustomerService(session).addcart(tablename,payload);
        } catch (SQLException e) {
            response.sendError(401, "SQL Error");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (CardExistException e) {
            response.sendError(409, "cart already exist");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SessionException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
