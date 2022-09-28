package com.util;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Validation {
    private String cateoryname;

    public void setCateoryname(String cateoryname) {
        this.cateoryname = cateoryname;
    }

    public void checkCateory() {
        RESTOperation rest = new RESTOperation();
        List<String> list = new ArrayList<String>();
        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.Cateorydata,
                Constant.AllCateory.cateoryname, cateoryname));
        try {
            while(resultdata.next()){
                list.add(resultdata.getString(Constant.AllCateory.cateoryname));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        if (list.size() == 0)
            rest.insert(Query.queryAddCateory(Constant.DataBase_UserTableName.Cateorydata, cateoryname));
    }

}
