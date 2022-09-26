package com.Login;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LoginService {
    public static boolean SignIn(HttpServletRequest request, Map<String, String> payload){
        RESTOperation rest = new RESTOperation();
        List<String> list = rest.find(Query.find(Constant.DataBase_UserTableName.DBUserdata,
                Constant.Usersdata.email,payload.get(Constant.Usersdata.email)));
        if (!(list.size() > 0 && (list.get(0).equals(payload.get(Constant.Usersdata.email))
                && list.get(1).equals(payload.get(Constant.Usersdata.password)))))
            return false;

        HttpSession session=request.getSession();
        session.setAttribute(Constant.Usersdata.userid,list.get(0));
        session.setAttribute(Constant.Usersdata.isadmin,list.get(2));

        System.out.println(session.getAttribute(Constant.Usersdata.userid));
        System.out.println(session.getAttribute(Constant.Usersdata.isadmin));
        return true;
    }
}
