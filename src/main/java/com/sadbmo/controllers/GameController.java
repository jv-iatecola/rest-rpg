package com.sadbmo.controllers;

import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.dtos.NewCharacterDto;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameController implements HttpHandler {
    private final JsonMapperAdapter mapper;
    private final SqlAdapter dbAdapter;

    public GameController(JsonMapperAdapter mapper, SqlAdapter dbAdapter) {
       this.mapper = mapper;
       this.dbAdapter = dbAdapter;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().equals("POST")) {
                this.createCharacter(exchange);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    private void createCharacter(HttpExchange exchange) throws Exception {
        InputStream inputStream = exchange.getRequestBody();

        NewCharacterDto characterDto = this.mapper.readValue(inputStream, NewCharacterDto.class);
        List<Object> params = new ArrayList<>();
        params.add(characterDto.characterName);
        params.add(characterDto.characterClass);

        this.dbAdapter.callProcedure("CALL add_character(?, ?)", params);

        String response = String.format("Welcome! %s the %s!", characterDto.characterName, characterDto.characterClass) ;
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }
}
