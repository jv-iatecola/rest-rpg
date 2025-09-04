package com.sadbmo.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class GameController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Print!");
            String response = "test";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
    }
}
