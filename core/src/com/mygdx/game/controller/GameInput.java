package com.mygdx.game.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.Street;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.systems.PlayerSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        if (keycode == Input.Keys.G) {
            for (Entity playerEntity : gameScreen.getEngine().getSystem(PlayerSystem.class).getEntities()) {
                final ModelComponent mod = playerEntity.getComponent(ModelComponent.class);
                Vector3 position = new Vector3();
                mod.getInstance().transform.getTranslation(position);

                PlayerComponent player = playerEntity.getComponent(PlayerComponent.class);
                try {
                    FileWriter writer = new FileWriter(new File("FieldCoords"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                System.out.println("Spieler " + player.getId() + ": " + position.toString());
            }
        }

        if (keycode == Input.Keys.M) {
            Entity p1 = gameScreen.selected;
            final PlayerComponent mod = p1.getComponent(PlayerComponent.class);
            mod.setMoney(mod.getMoney()-1000);
            System.out.println(mod.getMoney());
        }

        if (keycode == Input.Keys.J) {
            Entity p1 = gameScreen.selected;
            if (p1 != null) {
                PlayerComponent playerComponent = p1.getComponent(PlayerComponent.class);
                Street current = playerComponent.getCurrentStreet();

                // Fake 1 Roll
                Vector3 newPositon = playerComponent.move(1);

                final ModelComponent mod = p1.getComponent(ModelComponent.class);
                mod.getInstance().transform.setToTranslation(newPositon);
                mod.getInstance().calculateBoundingBox(mod.bounds);
                mod.bounds.mul(mod.getInstance().transform);
            }
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
        return true;
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
