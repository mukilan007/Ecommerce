package com.Login;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import com.util.Accountmanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginService {
    private RESTOperation rest = null;
    public LoginService(){
        rest = RESTOperation.getInstance();
    }
    public boolean SignIn(HttpServletRequest request, Map<String, String> payload) throws SQLException {
        List<String> list = new ArrayList<String>();
        String condition = " "+ Constant.Usersdata.email +" = '"+ payload.get(Constant.Usersdata.email) +"';";
        ResultSet resultdata = rest.executeQuery(Query.find(Constant.DataBase_UserTableName.DBUserdata, condition));
        try {
            while(resultdata.next()){
                list.add(resultdata.getString(Constant.Usersdata.userid));
                list.add(resultdata.getString(Constant.Usersdata.name));
                list.add(resultdata.getString(Constant.Usersdata.email));
                list.add(resultdata.getString(Constant.Usersdata.password));
                list.add(resultdata.getString(Constant.Usersdata.isadmin));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        if (!(list.size() > 0 && (list.get(2).equals(payload.get(Constant.Usersdata.email))
                && list.get(3).equals(payload.get(Constant.Usersdata.password)))))
            return false;

        HttpSession session=request.getSession();
        session.setAttribute(Constant.Usersdata.userid,list.get(0));
        session.setAttribute(Constant.Usersdata.name,list.get(1));
        session.setAttribute(Constant.Usersdata.isadmin,list.get(4));

        return true;
    }

    public boolean SignUp(HttpServletRequest request, Map<String, String> payload, String admin) throws SQLException {
        Accountmanagement accountmanagement = new Accountmanagement();
        payload.put(Constant.Usersdata.createdat, String.valueOf(accountmanagement.getCreatedAt()));
        payload.put(Constant.Usersdata.lastcheckin, String.valueOf(accountmanagement.getTimeNow()));
        payload.put(Constant.Usersdata.isdeleted, String.valueOf(accountmanagement.getDeleted()));
        payload.put(Constant.Usersdata.isadmin, admin);
        rest.executeUpdate(Query.queryAddUser(Constant.DataBase_UserTableName.DBUserdata, payload));

        if(SignIn(request, payload)){
            HttpSession session = request.getSession();
            String tablename = "orderhistory" + String.valueOf(session.getAttribute(Constant.Usersdata.userid));
            if(rest.checkTable(tablename)) {
                rest.createTable(Query.CreateUserHistoryTable(tablename));
            }
            return true;
        }
        return false;
    }
}
