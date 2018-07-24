package com.mygdx.game.stages.ActionCards;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.Effect;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.utils.GameAssets;
import com.mygdx.game.utils.ModelFactory;

import java.util.Arrays;
import java.util.List;

public class ActionCard extends Table implements Effect {

    Label text;
    List<Integer> amounts;
    ActionCardType type;

    public ActionCard(String text, ActionCardType type, Integer... amounts) {
        this(text, type);
        this.amounts = Arrays.asList(amounts);
    }

    public ActionCard(String text, ActionCardType type) {
        super(ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath));
        this.text = new Label(text, getSkin());
        this.text.setColor(Color.BLACK);
        this.text.setWrap(true);
        this.add(text);
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
                //playerComponent.goDirectToJail()
            case VAMPIRE:
                int amount = amounts.get(0);
                int otherPlayers = GameController.getPlayers().size() - 1;
                for (Entity entity : GameController.getPlayers()) {
                    PlayerComponent player = entity.getComponent(PlayerComponent.class);
                    if (player.equals(playerComponent)) {
                        continue;
                    }
                    player.setMoney(player.getMoney() + amount);
                }
                playerComponent.setMoney(playerComponent.getMoney() + (otherPlayers) * amount);
            case JAILFREE:
                //TODO Get Jail Free Card
            case RENOVATE:
                int[] buildings = playerComponent.getBuildingsCount();
                int cost = buildings[0] * amounts.get(0) + buildings[1] * amounts.get(1);
                playerComponent.setMoney(playerComponent.getMoney() - cost);
            case TRAVEL:
                //TODO
        }
    }

    public enum ActionCardType {
        GET, PAY, RENOVATE, JAILFREE, JAIL, VAMPIRE, GO, TRAVEL,
    }

    public ActionCardType getType() {
        return type;
    }

}


