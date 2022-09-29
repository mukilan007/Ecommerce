package com.customer;

import com.Constant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;

public class TableCart implements getSpecificTable{
    public JSONArray ResultSettoJSON(ResultSet resultdata) {
        JSONArray cateoryslist = new JSONArray();
        try {
            while(resultdata.next()){
                JSONObject cateorydetails =new JSONObject();
                cateorydetails.put(Constant.UserHistory.productid,
                        resultdata.getString(Constant.UserHistory.productid));
                cateorydetails.put(Constant.UserHistory.vendorid,
                        resultdata.getString(Constant.UserHistory.vendorid));
                cateorydetails.put(Constant.UserHistory.quantity,
                        resultdata.getString(Constant.UserHistory.quantity));
                cateorydetails.put(Constant.UserHistory.stage,
                        resultdata.getString(Constant.UserHistory.stage));
                cateoryslist.add(cateorydetails);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return cateoryslist;
    }
}
