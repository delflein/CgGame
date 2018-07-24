package com.mygdx.game.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.screens.GameScreen;

public class GameInput implements InputProcessor {

    private GameScreen gameScreen;

    public GameInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

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

        if(keycode == Input.Keys.LEFT) {
            final ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            mod.tra(-1,0,0);
            return false;
        }
        if(keycode == Input.Keys.UP) {
            final ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            mod.tra(0,0,-1);
            return false;
        }
        if(keycode == Input.Keys.RIGHT) {
            final ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            mod.tra(1,0,0);
            return false;
        }
        if(keycode == Input.Keys.DOWN) {
            final ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            mod.tra(0,0,1);
            return false;
        }

        if(keycode == Input.Keys.Z) {
            final ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            mod.getInstance().transform.rotate(Vector3.Y, 1);
            System.out.println("Turned Left");
            return false;
        }
        if(keycode == Input.Keys.U) {
            final ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            mod.getInstance().transform.rotate(Vector3.Y, -1);
            System.out.println("Turned Right");
            return false;
        }

        if(keycode == Input.Keys.C) {
            System.out.println(gameScreen.getCam().position);
        }

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gameScreen.selecting = gameScreen.getObject(screenX, screenY);
        return gameScreen.selecting != null;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Entity entity = gameScreen.getObject(screenX, screenY);
        if (entity == null) {
            gameScreen.setSelected(null);
        }
        if (gameScreen.selecting != null) {
            if (gameScreen.selecting == entity)
                gameScreen.setSelected(gameScreen.selecting);
            gameScreen.selecting = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (gameScreen.selecting == null)
            return false;
        if (gameScreen.selected == gameScreen.selecting) {
            Ray ray = gameScreen.getCam().getPickRay(screenX, screenY);
            final float distance = -ray.origin.y / ray.direction.y;
            Vector3 position = new Vector3();
            position.set(ray.direction).scl(distance).add(ray.origin);
            ModelComponent mod = gameScreen.selected.getComponent(ModelComponent.class);
            Vector3 old_position = new Vector3();
            mod.getInstance().transform.getTranslation(old_position);
            // Keep Height over NN
            position.y = old_position.y;
            mod.getInstance().transform.setTranslation(position);
            mod.getInstance().calculateBoundingBox(mod.bounds);
            mod.bounds.mul(mod.getInstance().transform);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
