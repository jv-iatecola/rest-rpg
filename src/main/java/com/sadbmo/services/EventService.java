package com.sadbmo.services;

public class EventService {

    public String foundItem (String foundItem) {
        return String.format("After exploring for what seems half an hour, you find an hidden chest containing an %s!", foundItem);
    }

    public String foundEnemy (String foundEnemy) {
        return String.format("You find yourself face-to-face with a %s", foundEnemy);
    }

    public String restoreSanity (int restoredSanity) {
        return String.format("""
            After traversing through a narrow path, you find yourself gazing at a majestic clearing, illuminated by the moon,
            this sight alone suffice to clear your mind from the terrors of the night, restore +%d sanity.
            """, restoredSanity);
    }
}
