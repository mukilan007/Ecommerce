package com.customer;

import org.json.simple.JSONArray;

import java.sql.ResultSet;

public interface getSpecificTable {
    JSONArray ResultSettoJSON(ResultSet resultdata);
}
