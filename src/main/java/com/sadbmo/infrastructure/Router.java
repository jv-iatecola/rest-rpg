package com.sadbmo.infrastructure;

import com.sadbmo.adapters.JacksonMapperAdapter;
import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.controllers.GameController;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Router {
    HttpServer server;

    public Router(HttpServer server) {
       this.server = server;
    }

    public void execute() {
        JsonMapperAdapter mapper = new JacksonMapperAdapter();
        HttpHandler gameController = new GameController(mapper);

        server.createContext("/anything", gameController);
    }
}
