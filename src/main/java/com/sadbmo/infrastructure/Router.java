package com.sadbmo.infrastructure;

import com.sadbmo.adapters.JacksonMapperAdapter;
import com.sadbmo.adapters.JdbcAdapter;
import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.controllers.GameController;
import com.sadbmo.repository.CharacterRepository;
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
        HttpHandler gameController = new GameController(mapper, characterRepository);

        server.createContext("/anything", gameController);
    }
}
