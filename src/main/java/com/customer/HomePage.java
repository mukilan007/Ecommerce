package com.customer;

import com.Constant;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/product")
public class HomePage extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray cateorys = new JSONArray();
        try {
            cateorys = new CustomerService().getAllCateory();
        } catch (SQLException e) {
            response.sendError(401, "Unauthorized");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println(cateorys.toString());
        PrintWriter out = response.getWriter();
        out.print(cateorys);
    }
}
