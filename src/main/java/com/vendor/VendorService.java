package com.vendor;

import com.Constant;
import com.db.Query;
import com.db.RESTOperation;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class VendorService {
    public static void addProduct(HttpServletRequest request, Map<String, String> payload) {
        RESTOperation rest = new RESTOperation();
        rest.insert(Query.queryAddProduct(Constant.DataBase_UserTableName.DBProductdata, payload));
    }

    public static void getProduct(HttpServletRequest request, @NotNull Map<String, String> payload) {
        //TODO: change payload into string  and get data from session
        RESTOperation rest = new RESTOperation();
        List<String> list = rest.find(Query.find(Constant.DataBase_UserTableName.DBProductdata,
                Constant.DataBase_Gobal_Products.vendorid ,payload.get(Constant.DataBase_Gobal_Products.vendorid)));
        //TODO: either we need to use jsonArray intends of list
    }
}
