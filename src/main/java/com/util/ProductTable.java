package com.util;

import com.Constant;
import com.customer.getSpecificTable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTable implements getSpecificTable {
    public JSONArray ResultSettoJSON(ResultSet resultdata) throws SQLException {
        JSONArray cateoryslist = new JSONArray();
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
}
