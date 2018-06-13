package com.mygdx.game.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.SelectableComponent;

public class EntityFactory {

    public static Entity createGameBoard(float x, float y, float z) {
        Model board = ModelFactory.loadModel("Board/Board.g3db");
        if (board == null) {
            return null;
        }

        final BoundingBox boundingBox = new BoundingBox();
        board.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(board, x, y, z);
        entity.add(modelComponent);

        return entity;
    }

    public static Entity createBox(float x, float y, float z, float width, float height, float depth, Color color) {
        Model box = ModelFactory.createBox(width, height, depth, color);
        if (box == null) {
            return null;
        }

        final BoundingBox boundingBox = new BoundingBox();
        box.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(box, x, y, z);
        SelectableComponent selectableComponent = new SelectableComponent(modelComponent);
        entity.add(modelComponent);
        entity.add(selectableComponent);

        return entity;
    }
}
