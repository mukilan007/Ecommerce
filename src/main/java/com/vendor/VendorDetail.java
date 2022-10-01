package com.vendor;

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
import java.net.UnknownServiceException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/vendor/detail")
public class VendorDetail extends HttpServlet {
    private String getUserID(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return String.valueOf(session.getAttribute(Constant.Usersdata.userid));
    }
    private String getTableName(HttpServletRequest req){
        HttpSession session = req.getSession();
        String userid = String.valueOf(session.getAttribute(Constant.Usersdata.userid));
        return "orderhistory" + userid;
    }
    protected VendorService vendorService = new VendorService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> payload = new BaseClass().getPayload(request);
        String userid = getUserID(request);

        String delivered_tablename = getTableName(request);

        JSONArray cateorys = new JSONArray();
        String order_tablename = Constant.DataBase_UserTableName.OrderDetail;
        String product_tablename = Constant.DataBase_UserTableName.DBProductdata;
        String stage = payload.get(Constant.UserHistory.stage);
        try {
//            cateorys = vendorService.getProduct(product_tablename, order_tablename,       //delivery
//                    payload.get(Constant.UserHistory.stage), userid);

            cateorys = vendorService.getDelivered(delivered_tablename,Constant.UserHistory.stage,stage);
        } catch (SQLException e) {
            response.sendError(401, "Unauthorized");
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        System.out.println(cateorys.toString());
        out.print(cateorys);
    }
}
