package com.mygdx.game.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.settings.GameSettings;
import com.mygdx.game.stages.ActionCards.ActionCard;
import com.mygdx.game.stages.UI.GameUI;

import java.util.List;

public class GameController implements Telegraph {

    private Engine engine;
    private static GameSettings settings;
    private static StateMachine<GameController, GameStates> gameStateMachine;
    private static GameUI gameUi;

    private static Entity currentPlayer;

    private int playerId = 0;
    private boolean moved;
    private static ImmutableArray<Entity> players;
    private static List<ActionCard> cards;


    public GameController(Engine engine, GameSettings gameSettings, GameUI stage) {
        this.engine = engine;
        settings = gameSettings;
        gameStateMachine = new DefaultStateMachine<GameController, GameStates>(this, GameStates.IDLE);
        players = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
        currentPlayer = players.get(0);
        gameUi = stage;
    }

    public static GameSettings getGameSettings() {
        return settings;
    }

    public static Entity getCurrentPlayer() {
        return currentPlayer;
    }

    public static ImmutableArray<Entity> getPlayers() {
        return players;
    }

    public static PlayerComponent getCurrentPlayerComponent() {
        return currentPlayer.getComponent(PlayerComponent.class);
    }

    public static GameUI getGameUi(){
        return gameUi;
    }

    public void nextPlayer() {
        playerId++;
        if (playerId == players.size()) {
            playerId = 0;
        }
        currentPlayer = players.get(playerId);
        PlayerComponent player = currentPlayer.getComponent(PlayerComponent.class);
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

    public static StateMachine<GameController, GameStates> getGameStateMachine() {
        return gameStateMachine;
    }

    public void update(float delta) {
        gameStateMachine.update();
    }

    public Engine getEngine() {
        return this.engine;
    }
}