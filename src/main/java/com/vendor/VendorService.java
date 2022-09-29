package com.vendor;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.util.Map;

public class VendorService {
    private static RESTOperation rest = null;
    public VendorService(){
        rest = RESTOperation.getInstance();
    }

    public static void getAllCateory(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.vendorid,
                (String) session.getAttribute(Constant.Usersdata.userid)
                ));
        JSONArray cateoryslist = new JSONArray();
        try {
            while(resultdata.next()){
                JSONObject cateorydetails =new JSONObject();
                cateorydetails.put(Constant.DataBase_Gobal_Products.categoryname,
                        resultdata.getString(Constant.DataBase_Gobal_Products.categoryname));
                cateoryslist.add(cateorydetails);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println(cateoryslist.toString());
        new vendorSetCookie().setcookie(response, cateoryslist);
    }
    public static void addProduct(HttpServletRequest request, Map<String, String> payload) {
        Validation validation = new Validation();
        validation.setCateoryname(payload.get("categoryname"));
        validation.checkCateory();

        HttpSession session = request.getSession();
        rest.insert(Query.queryAddProduct(Constant.DataBase_UserTableName.DBProductdata,
                (String) session.getAttribute(Constant.Usersdata.userid),payload));
    }

    public static void getProduct(HttpServletRequest request,  Map<String, String> payload) {
        //TODO: change payload into string  and get data from session

        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.vendorid ,payload.get(Constant.DataBase_Gobal_Products.vendorid)));
        //TODO: either we need to use jsonArray intends of list
    }
}
