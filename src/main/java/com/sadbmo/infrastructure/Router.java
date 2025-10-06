package com.sadbmo.infrastructure;

import com.sadbmo.adapters.JacksonMapperAdapter;
import com.sadbmo.adapters.JdbcAdapter;
import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.controllers.CharacterController;
import com.sadbmo.controllers.GameController;
import com.sadbmo.controllers.WorldController;
import com.sadbmo.controllers.utils.Utils;
import com.sadbmo.repositories.CharacterRepository;
import com.sadbmo.repositories.WorldRepository;

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
        Utils utils = new Utils();
        HttpHandler characterController = new CharacterController(mapper, characterRepository);
        HttpHandler worldController = new WorldController(mapper, worldRepository);
        HttpHandler gameController = new GameController(utils);


        server.createContext("/character", characterController);
        server.createContext("/world", worldController);
        server.createContext("/game", gameController);
    }
}
