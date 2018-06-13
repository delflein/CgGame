package com.mygdx.game.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.systems.RenderSystem;

public class GameInput implements InputProcessor {

    private GameScreen gameScreen;

    public GameInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
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
        return gameScreen.selecting >= 0;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int temp = gameScreen.getObject(screenX, screenY);
        if (temp == -1) {
            gameScreen.setSelected(temp);
        }
        if (gameScreen.selecting >= 0) {
            if (gameScreen.selecting == temp)
                gameScreen.setSelected(gameScreen.selecting);
            gameScreen.selecting = -1;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (gameScreen.selecting < 0)
            return false;
        if (gameScreen.selected == gameScreen.selecting) {
            Ray ray = gameScreen.getCam().getPickRay(screenX, screenY);
            final float distance = -ray.origin.y / ray.direction.y;
            Vector3 position = new Vector3();
            position.set(ray.direction).scl(distance).add(ray.origin);
            ImmutableArray<Entity> entities = gameScreen.getEngine().getSystem(RenderSystem.class).getEntities();
            ModelComponent mod = entities.get(gameScreen.selected).getComponent(ModelComponent.class);
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
