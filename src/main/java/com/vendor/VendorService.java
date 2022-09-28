package com.vendor;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.util.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class VendorService {
    public static void addProduct(HttpServletRequest request, Map<String, String> payload) {
        Validation validation = new Validation();
        validation.setCateoryname(payload.get("categoryname"));
        validation.checkCateory();
        RESTOperation rest = new RESTOperation();
        HttpSession session = request.getSession();
        rest.insert(Query.queryAddProduct(Constant.DataBase_UserTableName.DBProductdata,
                (String) session.getAttribute(Constant.Usersdata.userid),payload));
    }

    public static void getProduct(HttpServletRequest request, @NotNull Map<String, String> payload) {
        //TODO: change payload into string  and get data from session
        RESTOperation rest = new RESTOperation();
        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.vendorid ,payload.get(Constant.DataBase_Gobal_Products.vendorid)));
        //TODO: either we need to use jsonArray intends of list
    }
}
