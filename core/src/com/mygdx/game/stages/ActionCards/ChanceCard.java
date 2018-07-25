package com.mygdx.game.stages.ActionCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChanceCard extends ActionCard {

    String name = "Community Chest";

    static List<ChanceCard> chanceCards = Arrays.asList(
            new ChanceCard("Advance to Go (Collect $200))", ActionCardType.GO),
            new ChanceCard("Advance to Illinois Ave. - If you pass Go, collect $20", ActionCardType.GET, 20),
            new ChanceCard("Advance to St. Charles Place – If you pass Go, collect $200", ActionCardType.TRAVEL),
            new ChanceCard("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", ActionCardType.TRAVEL),
            //Next is twice within the Deck
            new ChanceCard("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", ActionCardType.TRAVEL),
            new ChanceCard("Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", ActionCardType.TRAVEL),
            new ChanceCard("Bank pays you dividend of $50", ActionCardType.GET, 50),
            new ChanceCard("Get out of Jail Free – This card may be kept until needed, or traded/sold", ActionCardType.JAILFREE),
            new ChanceCard("Go Back 3 Spaces", ActionCardType.TRAVEL),
            new ChanceCard("Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200", ActionCardType.JAIL),
            new ChanceCard("Make general repairs on all your property – For each house pay $25 – For each hotel $100", ActionCardType.RENOVATE, 25, 50),
            new ChanceCard("Pay poor tax of $15", ActionCardType.PAY, 15),
            new ChanceCard("Take a trip to Reading Railroad– If you pass Go, collect $200", ActionCardType.TRAVEL),
            new ChanceCard("You have been elected Chairman of the Board – Pay each player $50", ActionCardType.VAMPIRE, -50),
            new ChanceCard("Take a walk on the Boardwalk", ActionCardType.TRAVEL),
            new ChanceCard("You have won a crossword competition - Collect $100", ActionCardType.GET, 100),
            new ChanceCard("Your building loan matures – Collect $150", ActionCardType.GET, 150)
    );


    public ChanceCard(String text, ActionCardType type, Integer... amounts) {
        super(text,type,amounts);

    }

    public ChanceCard(String text, ActionCardType type) {
        super(text, type);
    }

    public static List<ChanceCard> getCards() {
        List<ChanceCard> result = new ArrayList<>(chanceCards);
        Collections.shuffle(result);
        return result;
    }
}
