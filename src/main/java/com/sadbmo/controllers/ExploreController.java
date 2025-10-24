package com.sadbmo.controllers;

import com.sadbmo.repositories.ExploreRepository;

import com.sadbmo.services.EventService;
import com.sadbmo.utils.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.LinkedHashMap;


public class ExploreController implements HttpHandler {
    private final Utils utils;
    private final EventService eventService;
    private final ExploreRepository exploreRepository;

    public ExploreController (Utils utils, EventService eventService, ExploreRepository exploreRepository) {

        this.utils = utils;
        this.eventService = eventService;
        this.exploreRepository = exploreRepository;
    }

    @Override
    public void handle (HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().equals("GET")) {
                this.explore(exchange);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void explore (HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        LinkedHashMap<String, Integer> params = utils.parseQuery(query);

        String response = null;

        try {
            int worldId = params.get("game_id");
            String gameMode = this.exploreRepository.getGameMode(worldId);

            response = this.eventService.eventManager(gameMode);
            exchange.sendResponseHeaders(200, response.length());


        } catch (Exception error) {
            response = String.format("Sorry, failed to explore due to error %s.", error);
            exchange.sendResponseHeaders(400, response.length());

        } finally {
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }
    }
}
