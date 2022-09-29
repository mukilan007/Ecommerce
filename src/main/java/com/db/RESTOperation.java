package com.db;

import com.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RESTOperation extends Query{
    private static volatile RESTOperation rest = null;
    private RESTOperation(){}
    public static RESTOperation getInstance(){
        if(rest == null){
            synchronized(RESTOperation.class) {
                if(rest == null) {
                    rest = new RESTOperation();
                }
            }
        }
        return rest;
    }

    public void createTable(String query){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("table created");
    }

    public boolean checkTable(String tableName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) "
                    + "from information_schema.tables "
                    + "where table_name = ? "
                    + "limit 1;");
            preparedStatement.setString(1, tableName);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) == 0;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return false;
    }
    public void createUserTable(String table_name){
       createTable(new Schema(table_name).createUserTable());
    }
    public void createProductTable(String table_name){
        createTable(new Schema(table_name).createGobalProduct());
    }

    public void insert(String Query){
        Statement statement = null;
        try {
            statement = connection.createStatement();
//            statement.executeQuery(Query);
            statement.executeUpdate(Query);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("inserted success");
    }

    public ResultSet find(String Query){
        Statement statement = null;
        ResultSet resultdata = null;
        try {
            statement = connection.createStatement();
            resultdata = statement.executeQuery(Query);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return resultdata;
    }

//    public void Update(String table_name, String old_value, String new_value) {
//        Statement statement = null;
//        ResultSet resultdata = null;
//        try {
//            String update = "update "+ table_name +" set e_mail=? where e_mail=? ";
//            preparedStatement = connection.prepareStatement(update);
//            preparedStatement.setString(1, old_value);
//            preparedStatement.setString(2, new_value);
//            statement = connection.createStatement();
//            resultdata = statement.executeQuery(String.valueOf(preparedStatement));
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println(e);
//        }
//    }
}
