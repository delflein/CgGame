package com.mygdx.game.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.GameSettings;
import com.mygdx.game.components.MovingComponent;
import com.mygdx.game.components.PlayerComponent;

import java.util.List;

public class GameController implements Telegraph {

    private Engine engine;
    private GameSettings settings;
    private StateMachine<GameController, GameStates> gameStateMachine;


    private int current_player = 0;
    private boolean moved;
    private ImmutableArray<Entity> players;


    public GameController(Engine engine, GameSettings settings) {
        this.engine = engine;
        this.settings = settings;
        gameStateMachine = new DefaultStateMachine<GameController, GameStates>(this, GameStates.IDLE);
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
                List<Vector3> path = player.moveSmooth(numOfFields);
                MovingComponent mov = entity.getComponent(MovingComponent.class);
                mov.setPath(path);
            }
        }
        nextPlayer();


    }

    public void nextPlayer() {

        if (players == null) {
            players = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
        }
        current_player++;
        if (current_player == players.size()) {
            current_player = 0;
        }
        PlayerComponent player = players.get(current_player).getComponent(PlayerComponent.class);
        if (player.isInJail()) {
            gameStateMachine.changeState(GameStates.JAIL);

        }
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }

    public StateMachine<GameController, GameStates> getGameStateMachine() {
        return gameStateMachine;
    }

    public void update(float delta) {
        gameStateMachine.update();
    }

    public Engine getEngine() {
        return this.engine;
    }
}