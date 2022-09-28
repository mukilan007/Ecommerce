package com.customer;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
