package com.util;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Validation {
    private static RESTOperation rest = null;
    private String cateoryname;
    public Validation(){
        rest = RESTOperation.getInstance();
    }

    public void setCateoryname(String cateoryname) {
        this.cateoryname = cateoryname;
    }

    public void checkCateory() throws SQLException {
        List<String> list = new ArrayList<String>();
        String condition = " "+ Constant.AllCateory.cateoryname +" = '"+ cateoryname +"';";
        ResultSet resultdata = rest.executeQuery(Query.find(Constant.DataBase_UserTableName.Cateorydata, condition));
        try {
            while(resultdata.next()){
                list.add(resultdata.getString(Constant.AllCateory.cateoryname));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        if (list.size() == 0)
            rest.executeUpdate(Query.queryAddCateory(Constant.DataBase_UserTableName.Cateorydata, cateoryname));
    }

}
