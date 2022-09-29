package com.customer;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerService {
    public static JSONArray getAllCateory(){
        RESTOperation rest = new RESTOperation();
        List<String> list = new ArrayList<String>();
        ResultSet resultdata = rest.find(Query.findall(Constant.DataBase_UserTableName.Cateorydata));
        JSONArray cateoryslist = new JSONArray();
        try {
            while(resultdata.next()){
                JSONObject cateorydetails =new JSONObject();
                cateorydetails.put(Constant.AllCateory.cateoryid,
                        resultdata.getString(Constant.AllCateory.cateoryid));
                cateorydetails.put(Constant.AllCateory.cateoryname,
                        resultdata.getString(Constant.AllCateory.cateoryname));
                cateoryslist.add(cateorydetails);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println(cateoryslist.toString());
        return cateoryslist;
    }

    public static JSONArray findproduct(Map<String, String> payload){
        RESTOperation rest = new RESTOperation();
        List<String> list = new ArrayList<String>();
        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.categoryname,
                payload.get(Constant.AllCateory.cateoryname)));
        JSONArray cateoryslist = new JSONArray();
        try {
            while(resultdata.next()){
                JSONObject cateorydetails =new JSONObject();
                cateorydetails.put(Constant.DataBase_Gobal_Products.product_name,
                        resultdata.getString(Constant.DataBase_Gobal_Products.product_name));
                cateorydetails.put(Constant.DataBase_Gobal_Products.categoryname,
                        resultdata.getString(Constant.DataBase_Gobal_Products.categoryname));
                cateorydetails.put(Constant.DataBase_Gobal_Products.detail,
                        resultdata.getString(Constant.DataBase_Gobal_Products.detail));
                cateorydetails.put(Constant.DataBase_Gobal_Products.quantity,
                        resultdata.getString(Constant.DataBase_Gobal_Products.quantity));
                cateorydetails.put(Constant.DataBase_Gobal_Products.size,
                        resultdata.getString(Constant.DataBase_Gobal_Products.size));
                cateorydetails.put(Constant.DataBase_Gobal_Products.color,
                        resultdata.getString(Constant.DataBase_Gobal_Products.color));
                cateorydetails.put(Constant.DataBase_Gobal_Products.brand_name,
                        resultdata.getString(Constant.DataBase_Gobal_Products.brand_name));
                cateorydetails.put(Constant.DataBase_Gobal_Products.price,
                        resultdata.getString(Constant.DataBase_Gobal_Products.price));
                cateorydetails.put(Constant.DataBase_Gobal_Products.type,
                        resultdata.getString(Constant.DataBase_Gobal_Products.type));

                cateoryslist.add(cateorydetails);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return cateoryslist;

    }
}
