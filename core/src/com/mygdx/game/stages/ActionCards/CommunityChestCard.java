package com.mygdx.game.stages.ActionCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommunityChestCard extends ActionCard {

    static List<CommunityChestCard> chestCards = Arrays.asList(
            //new CommunityChestCard("Advance to Go (Collect $200)", ActionCardType.GO),
            //new CommunityChestCard("Bank error in your favor – Collect $200", ActionCardType.GET, 200),
//            new CommunityChestCard("Doctor's fees Pay $50", ActionCardType.PAY, 50),
//            new CommunityChestCard("From sale of stock you get $50", ActionCardType.GET, 50),
//            new CommunityChestCard("Get Out of Jail Free - This card may be kept until needed or sold/traded)", ActionCardType.JAILFREE),
//            new CommunityChestCard("Go to Jail – Go directly to jail – Do not pass Go – Do not collect $200", ActionCardType.JAIL),
//            new CommunityChestCard("Grand Opera Night – Collect $50 from every player for opening night seats", ActionCardType.VAMPIRE, +50),
//            new CommunityChestCard("Holiday Fund matures - Receive $100", ActionCardType.GET, 100),
//            new CommunityChestCard("Income tax refund – Collect $20", ActionCardType.GET, 20),
//            new CommunityChestCard("Life insurance matures – Collect $100", ActionCardType.GET, 100),
//            new CommunityChestCard("Pay hospital fees of $100", ActionCardType.PAY, 100),
//            new CommunityChestCard("Pay school fees {tax} of $150", ActionCardType.PAY, 150),
//            new CommunityChestCard("Receive $25 consultancy fee", ActionCardType.GET, 25),
            new CommunityChestCard("You are assessed for street repairs – $40 per house – $115 per hotel", ActionCardType.RENOVATE, 40, 115)
//            new CommunityChestCard("You have won second prize in a beauty contest – Collect $10", ActionCardType.GET, 10),
            //new CommunityChestCard("You inherit $100", ActionCardType.GET, 100)
    );


    public CommunityChestCard(String text, ActionCardType type, Integer... amounts) {
        super(text, type, amounts);
    }

    public CommunityChestCard(String text, ActionCardType type) {
        super(text, type);
    }

    public static List<CommunityChestCard> getCards() {
        List<CommunityChestCard> result = new ArrayList<>(chestCards);
        Collections.shuffle(result);
        return result;
    }
}
