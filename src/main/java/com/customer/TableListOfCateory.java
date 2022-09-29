package com.customer;

import com.Constant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;

public class TableListOfCateory implements getSpecificTable{
    public JSONArray ResultSettoJSON(ResultSet resultdata) {
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
        return cateoryslist;
    }
}
