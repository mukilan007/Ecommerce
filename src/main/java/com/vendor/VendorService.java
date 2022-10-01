package com.vendor;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;

import com.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class VendorService {
    private static RESTOperation rest = null;
    public VendorService(){
        rest = RESTOperation.getInstance();
    }

    public JSONArray getAllCateory(String userid) throws SQLException {
        ResultSet resultdata = rest.executeQuery(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.vendorid,
                userid));
        JSONArray cateoryslist = new JSONArray();
        while(resultdata.next()){
            JSONObject cateorydetails =new JSONObject();
            cateorydetails.put(Constant.DataBase_Gobal_Products.categoryname,
                    resultdata.getString(Constant.DataBase_Gobal_Products.categoryname));
            cateoryslist.add(cateorydetails);
        }
        return cateoryslist;
    }
    public JSONArray getCateory(String userid) throws SQLException {
        ResultSet resultdata = rest.executeQuery(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.vendorid, userid));
        JSONArray productjson = new JSONArray();
        while(resultdata.next()){
            JSONObject productdetails =new JSONObject();
            productdetails.put(Constant.DataBase_Gobal_Products.product_name,
                    resultdata.getString(Constant.DataBase_Gobal_Products.product_name));
            productdetails.put(Constant.DataBase_Gobal_Products.brand_name,
                    resultdata.getString(Constant.DataBase_Gobal_Products.brand_name));
            productdetails.put(Constant.DataBase_Gobal_Products.color,
                    resultdata.getString(Constant.DataBase_Gobal_Products.color));
            productdetails.put(Constant.DataBase_Gobal_Products.size,
                    resultdata.getString(Constant.DataBase_Gobal_Products.size));
            productdetails.put(Constant.DataBase_Gobal_Products.quantity,
                    resultdata.getString(Constant.DataBase_Gobal_Products.quantity));
            productdetails.put(Constant.DataBase_Gobal_Products.price,
                    resultdata.getString(Constant.DataBase_Gobal_Products.price));
            productjson.add(productdetails);
        }
        return productjson;
    }
    public void addProduct(String userid, Map<String, String> payload) throws SQLException {
        Validation validation = new Validation();
        validation.setCateoryname(payload.get("categoryname"));         //TODO: change categoryname
        validation.checkCateory();

        rest.executeUpdate(Query.queryAddProduct(Constant.DataBase_UserTableName.DBProductdata,
                userid,payload));
    }

    public JSONArray getProduct(String tablename2, Map<String, String> payload) throws SQLException {
        ResultSet resultdata = rest.executeQuery(Query.findcart(Constant.DataBase_UserTableName.DBProductdata,
                tablename2,"payload"));
        JSONArray cateoryslist = new JSONArray();
        while(resultdata.next()){
            org.json.simple.JSONObject cateorydetails =new org.json.simple.JSONObject();
            cateorydetails.put(Constant.AllCateory.cateoryid,
                    resultdata.getString(Constant.AllCateory.cateoryid));
            cateorydetails.put(Constant.AllCateory.cateoryname,
                    resultdata.getString(Constant.AllCateory.cateoryname));
            cateoryslist.add(cateorydetails);
        }
//        System.out.println(cateoryslist.toString());
        return cateoryslist;
    }
}
