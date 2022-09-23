package com.Login;

import com.Constant;
import com.db.RESTOperation;

import java.util.List;

public class LoginService {
    public static void SignIn(String Payload){
//        System.out.println("print method");
        RESTOperation rest = new RESTOperation();
        List<String> list = rest.find(Constant.DataBase_UserTableName.DBUserdata, Payload);
        if(list.size() > 0 && list.get(0).equals(Payload)){
            System.out.println("same");
        }
        else{
            System.out.println("not same");
        }
    }
}
