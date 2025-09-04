package com.sadbmo.infrastructure;

import com.sadbmo.controllers.GameController;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Router {
    HttpServer server;

    public Router(HttpServer server) {
       this.server = server;
    }

    public void execute() {
        HttpHandler gameController = new GameController();

        server.createContext("/anything", gameController);
    }
}
