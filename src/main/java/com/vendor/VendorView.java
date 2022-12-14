package com.vendor;

import com.Constant;
import com.customer.SessionException;
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

@WebServlet("/vendor/view")
public class VendorView extends HttpServlet {
    public VendorView(){
        super();
    }
    private String getTableName(HttpServletRequest req){
        HttpSession session = req.getSession();
        String userid = String.valueOf(session.getAttribute(Constant.Usersdata.userid));
        return "orderhistory" + userid;
    }
    private String getUserId(HttpServletRequest req){
        HttpSession session = req.getSession();
        return  (String) session.getAttribute(Constant.Usersdata.userid);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        JSONArray categorys = new JSONArray();
        try {
            categorys = new VendorService(session).getCategory(getUserId(request));
        } catch (SQLException e) {
            response.sendError(401, "SQL Error");
            throw new RuntimeException(e);
        } catch (SessionException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println(categorys.toString());
        PrintWriter out = response.getWriter();
        out.print(categorys);
    }
}
