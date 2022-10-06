package com.vendor;

import com.Constant;
import com.base.BaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


@WebServlet("/vendor/delete")
public class VendorDelete extends HttpServlet {
    public VendorDelete(){
        super();
    }
    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);

        try {
            new VendorService().deleteProduct(payload.get(Constant.OrderDetail.id));
        } catch (SQLException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
