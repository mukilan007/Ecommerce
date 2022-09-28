package com.db;

import com.Constant;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Query {
    protected static Connection connection = null;
    protected static PreparedStatement preparedStatement = null;      //TODO: need to change into private


    public static String CreateCateoryTable(String table_name){
        return new Schema(table_name).createListOfCateory();
    }


    public static String find(String table_name,String value, String payload){
        String find  = "select * from "+ table_name +" where "+value+"=?";
        try {
            preparedStatement = connection.prepareStatement(find);
            preparedStatement.setString(1, payload);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }

    public static String findall(String table_name){
        String find  = "select * from "+ table_name;
        try {
            preparedStatement = connection.prepareStatement(find);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }

    public static String addUser(String table_name, Map<String, String> payload){          //TODO Map<String, String> payload
        try {
            String add = "insert into "+table_name+" (name,password,date_of_birth,e_mail,address,phone,is_admin," +
                    "created_at,last_check_in,is_deleted) values(?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, payload.get(Constant.Usersdata.name));
            preparedStatement.setString(2, payload.get(Constant.Usersdata.password));
            preparedStatement.setString(3, payload.get(Constant.Usersdata.dateofbirth));
            preparedStatement.setString(4, payload.get(Constant.Usersdata.email));
            preparedStatement.setString(5, payload.get(Constant.Usersdata.address));
            preparedStatement.setString(6, payload.get(Constant.Usersdata.phone));
            preparedStatement.setString(7, payload.get(Constant.Usersdata.isadmin));
            preparedStatement.setString(8, payload.get(Constant.Usersdata.createdat));
            preparedStatement.setString(9, payload.get(Constant.Usersdata.lastcheckin));
            preparedStatement.setString(10, payload.get(Constant.Usersdata.isdeleted));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }


    public static String queryAddProduct(String table_name,String vendor_id, Map<String, String> payload) {
        try {
            String add = "insert into "+table_name+" (vendor_id,product_name,category_name,detail,quantity,size,color," +
                    "brand_name,price,type) values(?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, vendor_id);
            preparedStatement.setString(2, payload.get("productname"));
            preparedStatement.setString(3, payload.get("categoryname"));
            preparedStatement.setString(4, payload.get("detail"));
            preparedStatement.setString(5, payload.get("quantity"));
            preparedStatement.setString(6, payload.get("size"));
            preparedStatement.setString(7, payload.get("color"));
            preparedStatement.setString(8, payload.get("brandname"));
            preparedStatement.setString(9, payload.get("price"));
            preparedStatement.setString(10, payload.get("type"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }

    public static String queryAddCateory(String table_name,  String payload) {
        try {
            String add = "insert into "+table_name+" (cateory_name) values (?);";
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, payload);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }
}
