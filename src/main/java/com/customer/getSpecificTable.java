package com.customer;

import org.json.simple.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface getSpecificTable {
    JSONArray ResultSettoJSON(ResultSet resultdata) throws SQLException;
}
