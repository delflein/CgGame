package com.mygdx.game.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.GameSettings;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.stages.UI.GameUI;

public class GameController {

    private Engine engine;
    private GameSettings settings;

    private int current_player = 0;

    public GameController(Engine engine, GameSettings settings){
        this.engine = engine;
        this.settings = settings;
    }

    public void movePlayer(int roll_a, int roll_b) {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
        for (Entity entity : entities) {
            PlayerComponent player = entity.getComponent(PlayerComponent.class);
            if(player.getId() == current_player) {
                if(player.isInJail()){
                    if(roll_a == roll_b) {
                        player.freeFromJail();
                    }
                }
                int numOfFields = roll_a + roll_b;
                Vector3 newPosition = player.move(numOfFields);
                ModelComponent mod = entity.getComponent(ModelComponent.class);
                mod.move(newPosition);
            }
        }
        nextPlayer();
    }

    public void nextPlayer() {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
        current_player++;
        if(current_player == entities.size()) {
            current_player = 0;
        }
        PlayerComponent player = entities.get(current_player).getComponent(PlayerComponent.class);
        if(player.isInJail()){
            player.lowerJailCount();
            nextPlayer();
        }
    }
}
