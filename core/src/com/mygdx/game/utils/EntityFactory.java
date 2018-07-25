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
        return getEntity(x, y, z, 0, false, GameAssets.BOARD);
    }

    public static Entity createChestCardstack(float x, float y, float z) {
        return getEntity(x, y, z, -45, false, GameAssets.CHEST_CARDSTACK);
    }

    public static Entity createChanceCardstack(float x, float y, float z) {
        return getEntity(x, y, z, -45, false, GameAssets.CHANCE_CARDSTACK);
    }

    public static Entity createTable(float x, float y, float z) {
        return getEntity(x, y, z, 0, false, GameAssets.TABLE);
    }

    public static Entity createHouse(float x, float y, float z, int degrees) {
        return getEntity(x, y, z, degrees, true, GameAssets.HOUSE);
    }

    public static Entity createHouse(Vector3 pos, int degrees) {
        return createHouse(pos.x, pos.y, pos.z, degrees);
    }

    public static Entity createHotel(float x, float y, float z, int degrees) {
        return getEntity(x, y, z, degrees, true, GameAssets.HOTEL);
    }

    public static Entity createHotel(Vector3 pos, int degrees) {
        return createHotel(pos.x, pos.y, pos.z, degrees);
    }

    private static Entity getEntity(float x, float y, float z, int degrees, boolean selectable, GameAssets asset) {
        Model model = ModelFactory.loadModel(asset.filepath);
        if(model == null) {
            return null;
        }

        final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);

        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y, z);
        modelComponent.getInstance().transform.rotate(Vector3.Y, degrees);
        entity.add(modelComponent);
        if(selectable) {
            SelectableComponent sel = new SelectableComponent(modelComponent);
            entity.add(sel);
        }

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
