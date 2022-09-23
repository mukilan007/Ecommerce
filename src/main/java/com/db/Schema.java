package com.db;

import com.Constant;

public class Schema {

    public String createUserTable(String table_name){
        return "create table "+table_name+"("+
                Constant.Usersdata.userid+" serial PRIMARY KEY," +
                Constant.Usersdata.name+" VARCHAR(50) UNIQUE NOT NULL," +
                Constant.Usersdata.password+" VARCHAR(50) NOT NULL," +
                Constant.Usersdata.dateofbirth+" DATE NOT NULL," +
                Constant.Usersdata.email+" VARCHAR(255) UNIQUE NOT NULL," +
                Constant.Usersdata.address+" VARCHAR(200) NOT NULL," +
                Constant.Usersdata.phone+" BIGINT NOT NULL," +
                Constant.Usersdata.isadmin+" BOOLEAN NOT NULL," +
                Constant.Usersdata.createdat+" TIMESTAMP," +
                Constant.Usersdata.lastcheckin+" TIMESTAMP," +
                Constant.Usersdata.isdeleted+" BOOLEAN NOT NULL" +
                ");";
//        return "create table "+table_name+"( user_id serial PRIMARY KEY, name varchar(50) NOT NULL);";
    }

    public String createListOfCateory(String table_name) {
        return "create table "+table_name+"(" +
                Constant.AllCateory.cateoryid+" serial PRIMARY KEY, " +
                Constant.AllCateory.cateoryname+" VARCHAR(100) NOT NULL" +
                ");";
    }

    public String createGobalProduct(String table_name) {
        return "create table "+table_name+"(" +
                Constant.DataBase_Gobal_Products.productid+" serial PRIMARY KEY," +
                Constant.DataBase_Gobal_Products.productownerid+" BIGINT NOT NULL," +
                Constant.DataBase_Gobal_Products.product_name+" VARCHAR(100) NOT NULL," +
                Constant.DataBase_Gobal_Products.detail+" VARCHAR(250) NOT NULL," +
                Constant.DataBase_Gobal_Products.quantity+" SMALLINT NOT NULL," +
                Constant.DataBase_Gobal_Products.size+" VARCHAR(50) NOT NULL," +
                Constant.DataBase_Gobal_Products.color+" VARCHAR(50) NOT NULL," +
                Constant.DataBase_Gobal_Products.brand_name+" VARCHAR(50) NOT NULL," +
                Constant.DataBase_Gobal_Products.price+" BIGINT NOT NULL," +
                Constant.DataBase_Gobal_Products.category+" VARCHAR(100) NOT NULL," +
                Constant.DataBase_Gobal_Products.type+" VARCHAR(100) NOT NULL" +
                ");";
    }
}
