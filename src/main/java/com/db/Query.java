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


    public static String find(String table_name,String value, @NotNull String payload){
        String find  = "select * from "+ table_name +" where "+value+"=?";
        try {
            preparedStatement = connection.prepareStatement(find);
            preparedStatement.setString(1, payload);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }

    public static String addUser(String table_name, @NotNull String[] payload){          //TODO Map<String, String> payload
        try {
            String add = "insert into "+table_name+" (name,password,DATE,e_mail,address,phone,is_admin," +
                    "created_at,last_check_in,is_deleted) values(?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, payload[0]);
            preparedStatement.setString(2, payload[1]);
            preparedStatement.setString(3, payload[2]);
            preparedStatement.setString(4, payload[3]);
            preparedStatement.setString(5, payload[4]);
            preparedStatement.setString(6, payload[5]);
            preparedStatement.setString(7, payload[6]);
            preparedStatement.setString(8, payload[7]);
            preparedStatement.setString(9, payload[8]);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }


    public static String queryAddProduct(String table_name, @NotNull Map<String, String> payload) {
        try {
            String add = "insert into "+table_name+" (vendor_id,product_name,category_name,detail,quantity,size,color," +
                    "brand_name,price,type) values(?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, payload.get("vendorid"));
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

    public static String queryAddCateory(String table_name, @NotNull Map<String, String> payload) {
        try {
            String add = "insert into "+table_name+" (cateory_name) values(?);";
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, payload.get("categoryname"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return String.valueOf(preparedStatement);
    }
}
