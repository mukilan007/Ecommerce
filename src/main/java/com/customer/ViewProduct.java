package com.customer;

import com.base.BaseClass;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/view/product")
public class ViewProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> payload = new BaseClass().getPayload(request);

        JSONArray cateorys =new CustomerService().findproduct(payload);
//        System.out.println(cateorys.toString());

        PrintWriter out = response.getWriter();
        out.print(cateorys);
    }
}
