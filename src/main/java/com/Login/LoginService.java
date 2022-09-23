package com.Login;

import com.Constant;
import com.db.RESTOperation;

import java.util.List;

public class LoginService {
    public static boolean SignIn(String email, String pass){
        RESTOperation rest = new RESTOperation();
        List<String> list = rest.find(Constant.DataBase_UserTableName.DBUserdata, email);
        return list.size() > 0 && (list.get(0).equals(email) && list.get(1).equals(pass));
    }
}
