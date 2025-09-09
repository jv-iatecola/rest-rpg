package com.sadbmo;

import com.sadbmo.infrastructure.Router;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class Main {
    public static void main(String[] args) throws Exception {
            int port = 8080;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("Server started on port " + port);

            Router router = new Router(server);
            router.execute();

            server.start();
    }

}
