package com.sadbmo.services;

import com.sadbmo.utils.Utils;
import java.util.Objects;

public class EventService {
    public final Utils utils;
    private static int counter = 0;
    private static EventService instance;

    private EventService(Utils utils){
        this.utils = utils;
    }

    public static EventService createInstance(Utils utils){
        if (Objects.isNull(instance)){
            return new EventService(utils);
        }
        return instance;
    }

    public String easyMode (int randomNumber) {
       String eventResponse;

        if (randomNumber == 0) {
            eventResponse = foundItem(utils.ItemSelector());
        } else if (randomNumber == 1){
            eventResponse = foundEnemy(utils.enemySelector());
        } else if (counter >= 3 && randomNumber == 2){
            eventResponse = nextEpisode();
        } else {
            eventResponse = affectSanity();
        }
        utils.ItemSelector();

        return eventResponse;
    }

    public String normalMode (int randomNumber) {
        String eventResponse;

        if (randomNumber == 0) {
            eventResponse = foundItem(utils.ItemSelector());
        } else if (randomNumber >= 1 && randomNumber <= 3){
            eventResponse = foundEnemy(utils.enemySelector());
        } else if (counter >= 5 && randomNumber == 4){
            eventResponse = nextEpisode();
        } else {
            eventResponse = affectSanity();
        }
        utils.ItemSelector();

        return eventResponse;
    }

    public String hardMode (int randomNumber) {
        String eventResponse;

        if (randomNumber == 0) {
            eventResponse = foundItem(utils.ItemSelector());
        } else if (randomNumber >= 1 && randomNumber <= 5){
            eventResponse = foundEnemy(utils.enemySelector());
        } else if (counter >= 6 && randomNumber == 6){
            eventResponse = nextEpisode();
        } else {
            eventResponse = affectSanity();
        }
        utils.ItemSelector();

        return eventResponse;
    }

    public String eventManager (String gameMode) {
        String eventResponse;
        int randomNumber;

        counter ++;

        switch (gameMode) {
            case "Easy" -> {
                randomNumber = utils.randomNumberGenerator(4);
                eventResponse = easyMode(randomNumber);
            }
            case "Normal" -> {
                randomNumber = utils.randomNumberGenerator(6);
                eventResponse = normalMode(randomNumber);
            }
            case "Hard" -> {
                randomNumber = utils.randomNumberGenerator(8);
                eventResponse = hardMode(randomNumber);
            }
            default -> {
                randomNumber = utils.randomNumberGenerator(4);
                eventResponse = normalMode(randomNumber);
            }
        }

        return eventResponse;
    }

    public String foundItem (String foundItem) {
        return String.format("After exploring for what seems half an hour, you find an hidden chest containing a %s!", foundItem);
    }

    public String foundEnemy (String foundEnemy) {
        return String.format("You find yourself face-to-face with a %s!", foundEnemy);
    }

    public String affectSanity () {
        String[] events = {
                "After traversing a narrow path, you find yourself gazing at a majestic clearing, illuminated by the moon; this sight alone suffices to clear your mind of the night horrors. +%d sanity.",

                "You stumble upon an old traveler who shares his campfire and a warm meal; his tales of courage rekindle your hope. +%d sanity.",

                "You trip over a root and fall into the mud; a mocking whisper fills your mind as you try to regain your composure... -%d sanity.",

                "A distant, eldritch, howl-like sound echoes through the woods, freezing your blood as shadows creep closer... -%d sanity."
        };

        return String.format(events[utils.randomNumberGenerator(4)], utils.randomNumberGenerator(1, 4));
    }

    public String nextEpisode () {
        return """
        After countless trials, you emerge from the heart of the cursed jungle. The whispering shadows fall silent, and the ancient corruption you once feared now lies broken beneath your will.
        Congratulations! You have cleared episode 1!
        """;
    }
}
