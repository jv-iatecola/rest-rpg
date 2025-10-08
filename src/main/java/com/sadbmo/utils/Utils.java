package com.sadbmo.utils;

import java.util.LinkedHashMap;

public class Utils {

    public LinkedHashMap<String, Integer> parseQuery(String query) {
        var params = new LinkedHashMap<String, Integer>();
        if (query == null || query.isEmpty()) {
            return params;
        }

        String[] pairs = query.split("&");


        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : null;

            try {
                Integer intValue = (value != null && !value.isEmpty()) ? Integer.parseInt(value) : null;

                params.put(key, intValue);
            } catch (NumberFormatException error) {
                params.put(key, null);
            }
        }
        return params;
    }
}
