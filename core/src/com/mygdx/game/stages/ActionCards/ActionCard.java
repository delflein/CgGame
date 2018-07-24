package com.mygdx.game.stages.ActionCards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.Effect;
import com.mygdx.game.components.PlayerComponent;

import java.util.Arrays;
import java.util.List;

public class ActionCard extends Table implements Effect {

    Label text;
    Skin skin;
    List<Integer> amounts;
    ActionCardType type;

    public ActionCard(String text, ActionCardType type, Integer... amounts) {
        this.text = new Label(text, skin);
        this.text.setColor(Color.BLACK);
        this.type = type;
        this.amounts = Arrays.asList(amounts);
    }

    public ActionCard(String text, ActionCardType type) {
        this.text = new Label(text, skin);
        this.text.setColor(Color.BLACK);
        this.type = type;
    }


    @Override
    public void effect(PlayerComponent playerComponent) {
        switch (this.type) {
            case GET:
                playerComponent.setMoney(playerComponent.getMoney() + amounts.get(0));
            case PAY:
                playerComponent.setMoney(playerComponent.getMoney() - amounts.get(0));
            case JAIL:
                //TODO Move to Jail
                //playerComponent.dosomemagichere
            case VAMPIRE:
                //TODO Suck money from other Players
            case JAILFREE:
                //TODO Get Jail Free Card
            case RENOVATE:
                //TODO Get amount of Buildings and sub costs
            case TRAVEL:
                //TODO go to Specific street
        }
    }

    public enum ActionCardType {
        GET, PAY, RENOVATE, JAILFREE, JAIL, VAMPIRE, GO, TRAVEL,
    }


}


