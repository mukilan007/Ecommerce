package com.Login;

import com.Constant;
import com.db.RESTOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginService {
    public static boolean SignIn(HttpServletRequest request, String email, String password){
        RESTOperation rest = new RESTOperation();
        List<String> list = rest.find(Constant.DataBase_UserTableName.DBUserdata, email);

        if (!(list.size() > 0 && (list.get(0).equals(email) && list.get(1).equals(password))))
            return false;

        HttpSession session=request.getSession();
        session.setAttribute(Constant.Usersdata.userid,list.get(0));
        session.setAttribute(Constant.Usersdata.isadmin,list.get(2));

        System.out.println(session.getAttribute(Constant.Usersdata.userid));
        System.out.println(session.getAttribute(Constant.Usersdata.isadmin));
        return true;
    }
}
