package com.sadbmo.infrastructure;

import com.sadbmo.adapters.JacksonMapperAdapter;
import com.sadbmo.adapters.JdbcAdapter;
import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.controllers.CharacterController;
import com.sadbmo.controllers.WorldController;
import com.sadbmo.repository.CharacterRepository;
import com.sadbmo.repository.WorldRepository;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Router {
    HttpServer server;

    public Router(HttpServer server) {
       this.server = server;
    }

    public void execute() throws Exception {
        JsonMapperAdapter mapper = new JacksonMapperAdapter();
        SqlAdapter dbAdapter = new JdbcAdapter();
        CharacterRepository characterRepository = new CharacterRepository(dbAdapter);
        WorldRepository worldRepository = new WorldRepository(dbAdapter);
        HttpHandler gameController = new CharacterController(mapper, characterRepository);
        HttpHandler worldController = new WorldController(mapper, worldRepository);


        server.createContext("/character", gameController);
        server.createContext("/world", worldController);
    }
}
