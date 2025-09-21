package com.sadbmo.controllers;

import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.dtos.NewCharacterDto;
import com.sadbmo.repository.CharacterRepository;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;

public class CharacterController implements HttpHandler {
    private final JsonMapperAdapter mapper;
    private final CharacterRepository characterRepository;

    public CharacterController(JsonMapperAdapter mapper, CharacterRepository characterRepository) {
       this.mapper = mapper;
       this.characterRepository = characterRepository;
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
        try {
            characterRepository.addCharacter(characterDto);
            int characterId = characterRepository.getCharacterId(characterDto.characterName);

            String response = String.format("Welcome! %s the %s! Your character id is %d", characterDto.characterName, characterDto.characterClass, characterId) ;
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();

        } catch (Exception error) {
            String response = String.format("Sorry, character name '%s' is already taken! Error %s", characterDto.characterName, error) ;
            exchange.sendResponseHeaders(400, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }
    }
}
