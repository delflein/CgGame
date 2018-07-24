package com.mygdx.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.components.PlayerComponent;

public class GameInput implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            System.exit(0);
        }
        /*if (keycode == Input.Keys.G) {
            List<Vector3> positions = new ArrayList<>();
            for (Entity playerEntity : gameScreen.getEngine().getSystem(PlayerSystem.class).getEntities()) {
                final ModelComponent mod = playerEntity.getComponent(ModelComponent.class);
                Vector3 position = new Vector3();
                mod.getInstance().transform.getTranslation(position);
                positions.add(position);
            }
            if(positions.size() == 4) {
                System.out.printf("new Vector3(%f, %f, %f), new Vector3(%f, %f, %f), new Vector3(%f, %f, %f), new Vector3(%f, %f, %f)", positions.get(0).x,positions.get(0).y,positions.get(0).z, positions.get(1).x,positions.get(1).y,positions.get(1).z,positions.get(2).x,positions.get(2).y,positions.get(2).z,positions.get(3).x,positions.get(3).y,positions.get(3).z);
            }
        }*/

        if (keycode == Input.Keys.M) {
            PlayerComponent player = GameController.getCurrentPlayerComponent();
            player.setMoney(player.getMoney()-1000);
            System.out.println(player.getMoney());
        }

        if (keycode == Input.Keys.B) {
            GameController.getCurrentPlayerComponent().moveSmooth(1);
            GameController.getGameStateMachine().changeState(GameStates.DICE);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
