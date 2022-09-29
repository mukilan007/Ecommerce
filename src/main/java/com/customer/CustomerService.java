package com.customer;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import com.util.Accountmanagement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.util.Map;

public class CustomerService {
    private RESTOperation rest = null;
    public CustomerService(){
        rest = RESTOperation.getInstance();
    }
    public JSONArray getAllCateory(String table_name, getSpecificTable strInstance){
        ResultSet resultdata = rest.find(Query.findall(table_name));
        JSONArray cateoryslist = strInstance.ResultSettoJSON(resultdata);
//        System.out.println(cateoryslist.toString());
        return cateoryslist;
    }

    public JSONArray findproduct(Map<String, String> payload){
        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.categoryname,
                payload.get(Constant.AllCateory.cateoryname)));
        JSONArray cateoryslist = new JSONArray();
        try {
            while(resultdata.next()){
                JSONObject cateorydetails =new JSONObject();
                cateorydetails.put(Constant.DataBase_Gobal_Products.productid,
                        resultdata.getString(Constant.DataBase_Gobal_Products.productid));
                cateorydetails.put(Constant.DataBase_Gobal_Products.vendorid,
                        resultdata.getString(Constant.DataBase_Gobal_Products.vendorid));
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

    public void addcart(HttpServletRequest request, Map<String, String> payload) {
        HttpSession session = request.getSession();
        String userid = String.valueOf(session.getAttribute(Constant.Usersdata.userid));
        String tablename = "orderhistory"+userid;
//        System.out.println("tablename " + tablename);

        if(rest.checkTable(tablename)) {
            rest.createTable(Query.CreateUserHistoryTable(tablename));
        }
        Accountmanagement accountmanagement = new Accountmanagement();
        payload.put(Constant.UserHistory.quantity, "1");
        payload.put(Constant.UserHistory.stage,"cart");
        payload.put(Constant.UserHistory.createdAt, String.valueOf(accountmanagement.getCreatedAt()));
        payload.put(Constant.UserHistory.completedAt, null);

        rest.insert(Query.queryAddCart(tablename, payload));
    }
}
