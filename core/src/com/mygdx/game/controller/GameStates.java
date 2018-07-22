package com.mygdx.game.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.MovingComponent;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.stages.UI.Dice;

import java.util.List;

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
            Entity entity = gc.getCurrentPlayer();
            PlayerComponent player = entity.getComponent(PlayerComponent.class);

            int numOfFields = rolls[0] + rolls[1];
            List<Vector3> path = player.moveSmooth(numOfFields);
            MovingComponent mov = entity.getComponent(MovingComponent.class);
            mov.setPath(path);


        }

        @Override
        public void update(GameController gc) {
            Entity entity = gc.getCurrentPlayer();
            MovingComponent mov = entity.getComponent(MovingComponent.class);
            if (!mov.isMoving()) {
                gc.getGameStateMachine().changeState(GameStates.BUILD);
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
            entity.nextPlayer();
            entity.getGameStateMachine().changeState(GameStates.IDLE);
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

}