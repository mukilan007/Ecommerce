package com.vendor;


import com.Constant;
import com.Login.LoginService;
import com.customer.CustomerService;
import com.util.vendorSetCookie;
import org.json.simple.JSONArray;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/vendorcookies")
public class VendorCookie extends HttpServlet {
    public VendorCookie() {super();}
    protected VendorService vendorService = new VendorService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        JSONArray cateorys = new JSONArray();
        try {
            cateorys = vendorService.getAllCateory((String) session.getAttribute(Constant.Usersdata.userid));
        } catch (Throwable e) {
            response.sendError(401, "Unauthorized");
            throw new RuntimeException(e);
        }
        new vendorSetCookie().setcookie(response, cateorys);
        System.out.println("cookie added");
        Cookie[] cookie1 = request.getCookies();
        String str = null;
        for(Cookie c: cookie1)
        {
            if(c.getName().equals("myproduct")){
                str = c.getValue();
            }
        }

        System.out.println(str);
        System.out.println(Base64.decode(str));
        System.out.println(Base64.decode(str).toString());
    }
}
