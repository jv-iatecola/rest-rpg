package com.sadbmo.controllers;

import com.sadbmo.adapters.JsonMapperAdapter;
import com.sadbmo.dtos.NewWorldDto;
import com.sadbmo.repository.WorldRepository;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;

public class WorldController implements HttpHandler {
    private final JsonMapperAdapter mapper;
    private final WorldRepository worldRepository;

    public WorldController(JsonMapperAdapter mapper, WorldRepository worldRepository) {
        this.mapper = mapper;
        this.worldRepository = worldRepository;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().equals("POST")) {
                this.createWorld(exchange);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void createWorld(HttpExchange exchange) throws Exception {
        InputStream inputStream = exchange.getRequestBody();

        NewWorldDto worldDto = this.mapper.readValue(inputStream, NewWorldDto.class);
        try {
            worldRepository.addWorld(worldDto);
            int worldId = worldRepository.getWorldId(worldDto.characterUuid);

            String response = String.format("World %d created successfully!", worldId);
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();

        } catch (Exception error) {
            String response = String.format("Failed to create world, error: %s.", error);
            exchange.sendResponseHeaders(400, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }
    }
}
