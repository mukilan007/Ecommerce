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

@WebServlet("/product")
public class HomePage extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();

//        JSONArray cateorys = new CustomerService().getAllCateory(Constant.DataBase_UserTableName.Cateorydata, new TableListOfCateory());
//        System.out.println(cateorys.toString());
//        out.print(cateorys);

//        out.print(new CustomerService().getAllCateory(Constant.DataBase_UserTableName.Cateorydata, new TableListOfCateory()));
        JSONArray cateorys = new JSONArray();
        cateorys = new CustomerService().getAllCateory();
        System.out.println(cateorys.toString());
        PrintWriter out = response.getWriter();
        out.print(cateorys);
    }
}
