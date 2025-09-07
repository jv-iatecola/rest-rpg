package com.sadbmo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadbmo.dtos.NewCharacterDto;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;

public class GameController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
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

        ObjectMapper mapper = new ObjectMapper();
        NewCharacterDto characterDto = mapper.readValue(inputStream, NewCharacterDto.class);

        Connection  connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bs", "postgres", "");
        CallableStatement callableStatement = connection.prepareCall("CALL add_character(?, ?)");
        callableStatement.setString(1, characterDto.characterName);
        callableStatement.setString(2, characterDto.characterClass);
        callableStatement.execute();

        String response = String.format("Welcome! %s the %s!", characterDto.characterName, characterDto.characterClass) ;
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }

}
