package test.testdb;

import com.Constant;
import com.db.RESTOperation;
import com.db.Query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testDBConnection {
//    @Before
//    public void setUp() {
//        DBConnection dbconnection = com.db.DBConnection.getDbObject();
//        Connection con = dbconnection.connectToDatabase(Constant.DataBaseName.Eco);
//    }

        public RESTOperation rest = new RESTOperation();
    public static void main(String[] args) {
//        rest.createUserTable(Constant.DataBase_UserTableName.DBUserdata);    //table create

//        rest.createProductTable(Constant.DataBase_UserTableName.DBProductdata);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Date date = new Date(System.currentTimeMillis());
//        String[] payload = {"mukilan", "password", String.valueOf(date), "mukilan@gmail.com", "xxx,yyy,zzz"
//                , "1234567890", "false", String.valueOf(timestamp), String.valueOf(timestamp), "false"};
//        rest.insert(Constant.DataBase_UserTableName.DBProductdata, payload);      //insert record


//        rest.view(Constant.DataBase_UserTableName.DBUserdata);      //view record


        //create table

//        new testCreateTable().createCateory();

        //User -> customer
        new testUserManipulation().finddata();

        //vendor
//        new testVendorManipulation().addGlobalProductsData();
//        new testVendorManipulation().addCateory();
    }
}
class testCreateTable extends testDBConnection{

    public void createCateory(){
        rest.createCateoryTable(Query.CreateCateoryTable(Constant.DataBase_UserTableName.Cateorydata));
    }
}

class testUserManipulation extends testDBConnection{
    public void finddata(){
        Map<String, String> payload = new HashMap<String, String>();
        payload.put(Constant.Usersdata.email, "mukilan@gmail.com");
        payload.put(Constant.Usersdata.password, "1234");


        List<String> list = new ArrayList<String>();
        ResultSet resultdata = rest.find(Query.find(Constant.DataBase_UserTableName.DBUserdata,
                Constant.Usersdata.email, payload.get(Constant.Usersdata.email)));      //view record
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
        if (list.size() > 0 && (list.get(2).equals(payload.get(Constant.Usersdata.email))
                && list.get(3).equals(payload.get(Constant.Usersdata.password)))) {
            System.out.println("same");
            for (String i : list)
                System.out.println(i);
        }
    }
}
class testVendorManipulation extends testDBConnection{
    public void addGlobalProductsData(){
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("vendorid", "1");
        payload.put("categoryname", "categoryname");
        payload.put("type", "type");
        payload.put("brandname","brandname");
        payload.put("productname","productname");
        payload.put("detail", "detail");
        payload.put("size", "10");
        payload.put("color", "color");
        payload.put("price", "1499");
        payload.put("quantity", "90");
        rest.insert(Query.queryAddProduct(Constant.DataBase_UserTableName.DBProductdata,"1", payload));
    }

    public void addCateory(){
        String payload = "categoryname";
        rest.insert(Query.queryAddCateory(Constant.DataBase_UserTableName.Cateorydata, payload));
    }
}
