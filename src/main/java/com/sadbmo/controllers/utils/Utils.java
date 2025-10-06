package com.sadbmo.controllers.utils;

import java.util.LinkedHashMap;

public class Utils {

    public LinkedHashMap<String, Integer> parseQuery(String query) {
        if (query == null || query.isEmpty()) {
            return new LinkedHashMap<String, Integer>();
        }

        String[] pairs = query.split("&");

        var params = new LinkedHashMap<String, Integer>();
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";
            Integer intValue = Integer.parseInt(value);

            params.put(key, intValue);
        }
        return params;
    }
}
