package com.db;

import com.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RESTOperation{
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    public RESTOperation(){
        DBConnection dbConnection = DBConnection.getDbObject();
        connection = dbConnection.connectToDatabase(Constant.DataBaseName.Eco);
    }

    public void createTable(String query){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("table created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    public void createUserTable(String table_name){
       createTable(new Schema(table_name).createUserTable());
    }
    public void createProductTable(String table_name){
        createTable(new Schema(table_name).createGobalProduct());
    }
    public void createCateoryTable(String table_name){
        createTable(new Schema(table_name).createListOfCateory());
    }
    public void createUserHistoryTable(String table_name){
        createTable(new Schema(table_name).createUserHistory());
    }

    public void insert(String table_name, String[] payload){
        Statement statement = null;
        try {
            String add = "insert into %s(name,password,DATE,e_mail,address,phone,is_admin," +
                    "created_at,last_check_in,is_deleted) values('%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                    "'%s');";
            statement = connection.createStatement();
            statement.executeUpdate(add);
            System.out.println("row added "+ payload[0]);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
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

    public List<String> find(String table_name, String value){
        Statement statement = null;
        ResultSet resultdata = null;
        List<String> result = new ArrayList<String>();
        try {
            String view  = "select * from "+ table_name +" where e_mail=?";
            preparedStatement = connection.prepareStatement(view);
            preparedStatement.setString(1, value);
            statement = connection.createStatement();
            resultdata = statement.executeQuery(String.valueOf(preparedStatement));
//            resultdata = statement.executeQuery(view);
            while(resultdata.next()){
                result.add(resultdata.getString(Constant.Usersdata.email));
                result.add(resultdata.getString(Constant.Usersdata.password));
                result.add(resultdata.getString(Constant.Usersdata.isadmin));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return result;
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
