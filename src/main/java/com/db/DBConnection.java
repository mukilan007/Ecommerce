package com.db;

import com.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class DBConnection {
    private static DBConnection dbObject = new DBConnection();
    private DBConnection(){}

    public static DBConnection getDbObject(){
        return dbObject;
    }

    public Connection connectToDatabase(String dbName) {
       Connection con= null;
       try{
           Class.forName("org.postgresql.Driver");
           con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/" + dbName,"postgres","admin");
//           if(con != null){
//               System.out.println("Connected to DB");
//           }else {
//               System.out.println("Connection Failed");
//           }
       }
       catch (Exception e){
           e.getStackTrace();
       }
       return con;
    }
}

