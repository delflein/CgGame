package com.mygdx.game.controller;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

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
        public void enter(GameController entity) {

        }

        @Override
        public void update(GameController entity) {

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
    },

}