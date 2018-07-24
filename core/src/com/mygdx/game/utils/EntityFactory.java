package com.mygdx.game.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.MovingComponent;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.SelectableComponent;

public class EntityFactory {

    public static Entity createGameBoard(float x, float y, float z) {
        Model board = ModelFactory.loadModel(GameAssets.BOARD.filepath);
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

    public static Entity createChestCardstack(float x, float y, float z) {
        Model cardstack = ModelFactory.loadModel(GameAssets.CHEST_CARDSTACK.filepath);
        if(cardstack == null) {
            return null;
        }

        final BoundingBox boundingBox = new BoundingBox();
        cardstack.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(cardstack, x, y, z);
        modelComponent.getInstance().transform.rotate(Vector3.Y, -45);
        SelectableComponent selectableComponent = new SelectableComponent(modelComponent);
        entity.add(modelComponent);
        entity.add(selectableComponent);

        return entity;
    }

    public static Entity createChanceCardstack(float x, float y, float z) {
        Model cardstack = ModelFactory.loadModel(GameAssets.CHANCE_CARDSTACK.filepath);
        if(cardstack == null) {
            return null;
        }

        final BoundingBox boundingBox = new BoundingBox();
        cardstack.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(cardstack, x, y, z);
        modelComponent.getInstance().transform.rotate(Vector3.Y, -45);
        SelectableComponent selectableComponent = new SelectableComponent(modelComponent);
        entity.add(modelComponent);
        entity.add(selectableComponent);

        return entity;
    }

    public static Entity createPlayer() {
        PlayerComponent player = new PlayerComponent();
        Vector3 curr_pos = player.getCurrentStreet().getPosition(player);

        int id = player.getId();
        GameAssets asset;
        switch (id) {
            case 0:
                asset = GameAssets.TOKEN_1;
                break;
            case 1:
                asset = GameAssets.TOKEN_2;
                break;
            case 2:
                asset = GameAssets.TOKEN_3;
                break;
            case 3:
                asset = GameAssets.TOKEN_4;
                break;
            default:
                asset = GameAssets.TOKEN_1;
        }
        Model box = ModelFactory.loadModel(asset.filepath);//ModelFactory.createBox(width, height, depth, color);
        if (box == null) {
            return null;
        }

        final BoundingBox boundingBox = new BoundingBox();
        box.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(box, curr_pos.x, curr_pos.y, curr_pos.z);
        SelectableComponent selectableComponent = new SelectableComponent(modelComponent);
        MovingComponent movingComponent = new MovingComponent(modelComponent);
        entity.add(modelComponent);
        entity.add(selectableComponent);
        entity.add(movingComponent);
        entity.add(player);

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
        MovingComponent movingComponent = new MovingComponent(modelComponent);
        entity.add(modelComponent);
        entity.add(selectableComponent);
        entity.add(movingComponent);

        return entity;
    }
}
