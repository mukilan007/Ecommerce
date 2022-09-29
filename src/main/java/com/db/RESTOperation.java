package com.db;

import com.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class RESTOperation extends Query{
    public RESTOperation(){             //TODO: need to change into singleton
        DBConnection dbConnection = DBConnection.getDbObject();
        connection = dbConnection.connectToDatabase(Constant.DataBaseName.Eco);
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
    public void createUserTable(String table_name){
       createTable(new Schema(table_name).createUserTable());
    }
    public void createProductTable(String table_name){
        createTable(new Schema(table_name).createGobalProduct());
    }
    public void createCateoryTable(String Query){
        createTable(Query);
    }
    public void createUserHistoryTable(String table_name){
        createTable(new Schema(table_name).createUserHistory());
    }

    public void insert(String Query){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeQuery(Query);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("inserted success");
    }

    public void view(String table_name){
        Statement statement = null;
        ResultSet resultdata = null;
        try {
            String view  = String.format("select * from "+ table_name );
            statement = connection.createStatement();
            resultdata = statement.executeQuery(view);
            while(resultdata.next()){
                System.out.print(resultdata.getString(Constant.Usersdata.name) + " ");
                System.out.println(resultdata.getString(Constant.Usersdata.email) + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
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

    public void Update(String table_name, String old_value, String new_value) {
        Statement statement = null;
        ResultSet resultdata = null;
        try {
            String update = "update "+ table_name +" set e_mail=? where e_mail=? ";
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, old_value);
            preparedStatement.setString(2, new_value);
            statement = connection.createStatement();
            resultdata = statement.executeQuery(String.valueOf(preparedStatement));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
