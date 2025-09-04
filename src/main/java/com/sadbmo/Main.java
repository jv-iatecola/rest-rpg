package com.sadbmo;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class Main {
    public static void main(String[] args) throws IOException {
            int port = 8080;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("Server started on port " + port);
            server.createContext("/anything", exchange -> {
                System.out.println("Print!");
                String response = "test";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            });

            server.start();
    }
}
