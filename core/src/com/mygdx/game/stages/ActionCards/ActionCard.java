package com.mygdx.game.stages.ActionCards;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.Effect;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.Street;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.utils.GameAssets;
import com.mygdx.game.utils.ModelFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionCard extends Table implements Effect {

    Label text;
    List<Integer> amounts = new ArrayList<>();
    ActionCardType type;
    Skin skin= ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath);

    public ActionCard(String text, ActionCardType type, Integer... amounts) {
        this(text, type);
        this.amounts = Arrays.asList(amounts);
    }

    public ActionCard(String text, ActionCardType type) {
        super();
        this.debug();
        this.text = new Label("",skin);
        this.text.setColor(Colors.get("BLACK"));
        this.text.setText(text);
        this.type = type;
        this.add(this.text);
    }


    @Override
    public void effect(PlayerComponent playerComponent) {
        switch (this.type) {
            case GET:
                playerComponent.setMoney(playerComponent.getMoney() + amounts.get(0));
                break;
            case PAY:
                playerComponent.setMoney(playerComponent.getMoney() - amounts.get(0));
                break;
            case JAIL:
                playerComponent.moveTo(Street.getStreetByName("Jail"));
                playerComponent.setJail();
                break;
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
                break;
            case JAILFREE:
                //TODO Get Jail Free Card
                break;
            case RENOVATE:
                int[] buildings = playerComponent.getBuildingsCount();
                int cost = buildings[0] * amounts.get(0) + buildings[1] * amounts.get(1);
                playerComponent.setMoney(playerComponent.getMoney() - cost);
                break;
            case TRAVEL:
                //TODO
                break;
        }
    }

    public enum ActionCardType {
        GET, PAY, RENOVATE, JAILFREE, JAIL, VAMPIRE, GO, TRAVEL,
    }


    public ActionCardType getType() {
        return type;
    }

}


