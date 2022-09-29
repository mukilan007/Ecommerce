package com.vendor;


import com.customer.CustomerService;
import org.json.simple.JSONArray;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/vendorcookies")
public class VendorCookie extends HttpServlet {
    public VendorCookie() {super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VendorService.getAllCateory(request, response);
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
