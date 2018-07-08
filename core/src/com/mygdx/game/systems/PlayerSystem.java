package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mygdx.game.components.PlayerComponent;

public class PlayerSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    public ImmutableArray<Entity> getEntities() {
        return this.entities;
    }

    public void addedToEngine(Engine e) {
        entities = e.getEntitiesFor(Family.all(PlayerComponent.class).get());
    }
}
