package com.sadbmo.utils;

import java.security.SecureRandom;
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

    public int randomNumberGenerator(int range){
        return randomNumberGenerator(0, range);
    }

    public int randomNumberGenerator(int start, int end){
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(start, end);
    }

    public String ItemSelector(){
        String[] lootItems = {
                "Jaguar Fang Dagger",
                "Vine-Wrapped Bow",
                "Tanglewood Club",
                "Healing Elixir",
                "Sanity Tonic",
                "Poison Ant Venom",
                "Gold Pouch",
                "Moonlit Amulet",
                "Jungle Herbs",
                "Sacred Idol Fragment"
        };

        return lootItems[randomNumberGenerator(lootItems.length)];
    }

    public String enemySelector(){
        String[] jungleEnemies = {
                "Wild Boar",
                "Poisonfang Viper",
                "Tribal Hunter",
                "Eldritch Horror",
                "Giant Centipede",
                "Swamp Leech",
                "Rogue Explorer",
                "Forest Spider",
                "Howling Monkey",
                "Thornback Lizard"
        };
        return jungleEnemies[randomNumberGenerator(jungleEnemies.length)];
    }
}
