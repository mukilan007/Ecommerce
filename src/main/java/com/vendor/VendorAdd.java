package com.vendor;

import com.Constant;
import com.base.BaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/vendor/add")
public class VendorAdd extends HttpServlet {
    public VendorAdd(){
        super();
    }

    protected VendorService vendorService = new VendorService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);

        HttpSession session = request.getSession();
        try {
            vendorService.addProduct((String) session.getAttribute(Constant.Usersdata.userid),payload);
        } catch (SQLException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
