package test.testdb;

import com.Constant;
import com.db.DBConnection;
import com.db.RESTOperation;

import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class testDBConnection {
//    @Before
//    public void setUp() {
//        DBConnection dbconnection = com.db.DBConnection.getDbObject();
//        Connection con = dbconnection.connectToDatabase(Constant.DataBaseName.Eco);
//    }

    public static void main(String[] args) {

        RESTOperation rest = new RESTOperation();

//        rest.createTable(Constant.DataBase_UserTableName.DBUserdata);    //table create

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        String[] payload = {"mukilan", "password", String.valueOf(date), "mukilan@gmail.com", "xxx,yyy,zzz"
                , "1234567890", "false", String.valueOf(timestamp), String.valueOf(timestamp), "false"};
//        rest.insert(con, Constant.DataBase_UserTableName.DBUserdata, payload);      //insert record


//        rest.view(Constant.DataBase_UserTableName.DBUserdata);      //view record

        String email = "mukilan@gmail.com";
        List<String> list = rest.find(Constant.DataBase_UserTableName.DBUserdata, email);      //view record
        for(String i : list){
            System.out.println(i);
        }
    }
}
