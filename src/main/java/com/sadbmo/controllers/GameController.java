package com.sadbmo.controllers;

import com.sadbmo.utils.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.LinkedHashMap;

public class GameController implements HttpHandler {
    private final Utils utils;

    public GameController(Utils utils) {
        this.utils = utils;
    };

    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().equals("GET")) {
                this.startGame(exchange);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void startGame(HttpExchange exchange) throws Exception{
        String query = exchange.getRequestURI().getQuery();
        LinkedHashMap<String, Integer> params = utils.parseQuery(query);

        try {
            int saveId = params.get("saveid");
            int episode = params.get("ep");

            String response = """
            You wake up from a dreamless sleep, alone, with no recollection on how you got here, amidst a lush, green jungle.
            You look up to try and find some answers, only to discover that the sun is already setting. You must act quickly, what will you do? Explore, Bag, Retreat
            """;
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();

        } catch (Exception error) {
            String response = String.format("Sorry, failed to load the game. Error: $s", error);
            exchange.sendResponseHeaders(400, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }
    }
}
