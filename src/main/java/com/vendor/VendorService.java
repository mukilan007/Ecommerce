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
        String condition = " "+ Constant.DataBase_Gobal_Products.vendorid +" = '"+ userid +"';";
        ResultSet resultdata = rest.executeQuery(Query.find(Constant.DataBase_UserTableName.DBProductdata, condition));
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
        String condition = " "+ Constant.DataBase_Gobal_Products.vendorid +" = '"+ userid +"';";
        ResultSet resultdata = rest.executeQuery(Query.find(Constant.DataBase_UserTableName.DBProductdata, condition));
        return new ProductTable().ResultSettoJSON(resultdata);
    }
    public void addProduct(String userid, Map<String, String> payload) throws SQLException {
        Validation validation = new Validation();
        validation.setCateoryname(payload.get("categoryname"));         //TODO: change categoryname
        validation.checkCateory();

        rest.executeUpdate(Query.queryAddProduct(Constant.DataBase_UserTableName.DBProductdata,
                userid,payload));
    }

    public JSONArray getProduct(String product_tablename, String order_tablename, String stage, String userid) throws SQLException {
        String condition = " "+ Constant.UserHistory.stage +" = '"+ stage +"' " +
                " and "+ order_tablename+"."+ Constant.UserHistory.vendorid +" = '"+ userid +"';";
        ResultSet resultdata = rest.executeQuery(Query.findcart(product_tablename, order_tablename, condition));
        return new ProductTable().ResultSettoJSON(resultdata);
    }

    public JSONArray getDelivered(String delivered_tablename, String stagename, String stage) throws SQLException {
        String condition = " "+ stagename +" = '"+ stage +"';";
        ResultSet resultdata = rest.executeQuery(Query.find(delivered_tablename, condition));
        return new ProductTable().ResultSettoJSON(resultdata);
    }
}
