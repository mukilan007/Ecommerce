package com.base;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class BaseClass {
    public Map<String, String> getPayload(HttpServletRequest request){
        String payload = request.getParameter("payload");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> maps = null;
        try {
            maps = mapper.readValue(payload, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
}
