package com.mygdx.game.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.game.components.MovingComponent;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.Street;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.stages.UI.Dice;
import com.mygdx.game.stages.UI.WinStage;

public enum GameStates implements State<GameController> {

    IDLE() {
        @Override
        public void enter(GameController entity) {
            entity.setMoved(false);
        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    },

    DICE() {
        @Override
        public void enter(GameController gc) {
            int[] rolls = Dice.getRoll();
            Entity entity = GameController.getCurrentPlayer();
            PlayerComponent player = entity.getComponent(PlayerComponent.class);

            int numOfFields = rolls[0] + rolls[1];
            player.moveSmooth(numOfFields);
        }

        @Override
        public void update(GameController gc) {
            Entity entity = GameController.getCurrentPlayer();
            MovingComponent mov = entity.getComponent(MovingComponent.class);
            if (!mov.isMoving()) {
                GameController.getGameStateMachine().changeState(GameStates.FIELD);
            }
        }

        @Override
        public void exit(GameController entity) {
            entity.setMoved(true);
        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    },
    FIELD() {
        @Override
        public void enter(GameController gc) {
            Entity entity = GameController.getCurrentPlayer();
            PlayerComponent player = entity.getComponent(PlayerComponent.class);
            player.getCurrentStreet().effect(player);
            if(Dice.isSame()){
               if(player.increaseSame()){
                   player.moveTo(Street.getStreetByName("Jail"));
                   player.setSame(0);
               }
               GameController.getGameStateMachine().changeState(GameStates.IDLE);
               return;
            }
            GameController.getGameStateMachine().changeState(GameStates.BUILD);
        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    },
    BUY() {
        @Override
        public void enter(GameController gc) {
            GameController.getCurrentPlayerComponent().buyStreet();
            if (Dice.isSame()){
                GameController.getGameStateMachine().changeState(GameStates.IDLE);
                return;
            }
            GameController.getGameStateMachine().changeState(GameStates.BUILD);
        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    },

    TRADE() {
        @Override
        public void enter(GameController entity) {

        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    },

    NEXT_PLAYER() {
        @Override
        public void enter(GameController entity) {
            Entity playerentity = GameController.getCurrentPlayer();
            PlayerComponent player = GameController.getCurrentPlayerComponent();
            if (player.getMoney()<0){
                if (GameController.getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).size()<=2){
                    GameController.getGameStateMachine().changeState(GameStates.WIN);
                    return;
                }
                GameController.getEngine().removeEntity(playerentity);
            }
            entity.nextPlayer();
            GameController.getGameStateMachine().changeState(GameStates.IDLE);

        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    },

    JAIL() {
        @Override
        public void enter(GameController entity) {
            /*if(player.isInJail()){
                if(roll_a == roll_b) {
                    player.freeFromJail();
                }
            }*/
            //player.lowerJailCount();
            // nextPlayer();
        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    }, BUILD() {
        @Override
        public void enter(GameController entity) {
            if (Dice.isSame()){GameController.getGameStateMachine().changeState(GameStates.IDLE);}
        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            return false;
        }
    }, WIN() {
        @Override
        public void enter(GameController entity) {
            GameController.getGameScreen().setStage(new WinStage(GameController.getCurrentPlayerComponent()));
        }

        @Override
        public void update(GameController entity) {

        }

        @Override
        public void exit(GameController entity) {

        }

        @Override
        public boolean onMessage(GameController entity, Telegram telegram) {
            System.exit(0);
            return true;

        }
    }


}